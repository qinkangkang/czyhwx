package com.czyh.czyhwx.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czyh.czyhwx.config.MainConfig;
import com.czyh.czyhwx.dao.CustomerBargainingDAO;
import com.czyh.czyhwx.dao.CustomerDAO;
import com.czyh.czyhwx.dao.CustomerTicketDAO;
import com.czyh.czyhwx.dao.EventBargainingDAO;
import com.czyh.czyhwx.dao.SceneUserDAO;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.entity.TCustomer;
import com.czyh.czyhwx.entity.TCustomerBargaining;
import com.czyh.czyhwx.entity.TCustomerTicket;
import com.czyh.czyhwx.entity.TEventBargaining;
import com.czyh.czyhwx.entity.TSceneUser;
import com.czyh.czyhwx.service.CommonService;
import com.czyh.czyhwx.service.CoreService;
import com.czyh.czyhwx.service.GamesService;
import com.google.common.collect.Maps;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class GamesServiceImpl implements GamesService {

	private static final Logger logger = LoggerFactory.getLogger(GamesServiceImpl.class);

	@Autowired
	private CustomerBargainingDAO customerBargainingDAO;

	@Autowired
	private EventBargainingDAO eventBargainingDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private SceneUserDAO sceneUserDAO;
	
	@Autowired
	private CustomerTicketDAO customerTicketDAO;
	
	@Autowired
	private CoreService coreService;
	
	@Autowired
	private CommonService commonService;

	@Override
	@Transactional(readOnly = true)
	public ResponseDTO getShareHelp(String customerBargainingId, String ticket, Integer clientType) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(customerBargainingId)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(201);
			responseDTO.setMsg("当前参数不存在请检查customerBargainingId！");
			return responseDTO;
		}

		if (StringUtils.isBlank(ticket)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(201);
			responseDTO.setMsg("ticket参数不能为空，请检查ticket的传递参数值！");
			return responseDTO;
		}
		TCustomerTicket tCustomerTicket = customerTicketDAO.getByFticketAndFtype(ticket, clientType);
		if (tCustomerTicket == null) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(100);
			responseDTO.setMsg("ticket参数有误，这是个无效或者过期的ticket信息！");
			return responseDTO;
		}

		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();
		Map<String, Object> returnData = Maps.newHashMap();
		try {
			TCustomerBargaining tCustomerBargaining = customerBargainingDAO.getOne(customerBargainingId);

			TEventBargaining tEventBargaining = eventBargainingDAO.getOne(tCustomerBargaining.getFbargainingId());

			TCustomer tCustomer = customerDAO.getOne(tCustomerTicket.getFcustomerId());

			TSceneUser tSceneUser = sceneUserDAO.findBySub(tCustomer.getFweixinId());

			DateTime startDate = null;
			DateTime endDate = null;
			int interval = 0;
			startDate = new DateTime(tEventBargaining.getFbeginTime());
			// 活动结束时间
			endDate = new DateTime(tEventBargaining.getFendTime());
			// 计算出活动起止时间的分钟间隔数
			interval = Seconds.secondsBetween(startDate, endDate).getSeconds();

			WxMpQrCodeTicket ticketwx = wxMpService.getQrcodeService()
					.qrCodeCreateTmpTicket(Integer.parseInt(tCustomerBargaining.getFbargainingNum()), interval);
			String tmpQrCodeUrl = wxMpService.getQrcodeService().qrCodePictureUrl(ticketwx.getTicket());// 这是微信提供的二维码httpurl
			returnData.put("tmpQrCodeUrl", tmpQrCodeUrl);
			if (tSceneUser != null) {
				if (tSceneUser.getFsubscribe() != null && tSceneUser.getFsubscribe() == 1) {
					returnData.put("subscribe", 1);
				} else {
					returnData.put("subscribe", 0);
				}
			} else {
				returnData.put("subscribe", 0);
			}
		} catch (WxErrorException e) {
			logger.error(e.getError().getErrorMsg());
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("砍一砍分享成功");
		responseDTO.setData(returnData);
		return responseDTO;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<TSceneUser> testMo() {
		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> returnData = Maps.newHashMap();
		
//		StringBuilder hql = new StringBuilder();
//		hql.append("select t.fweixinId as fweixinId from TCustomer t where t.fweixinId not in")
//				.append(" (select s.fopenId from TSceneUser s)").append(" order by t.fcreateTime desc ");
//
//		Map<String, Object> hqlMap = new HashMap<String, Object>();
//		List<Map<String, Object>> list = commonService.find(hql.toString(), hqlMap);
//		for (Map<String, Object> map : list) {
//			try {
//				WxMpUser wxMpUser = coreService.getUserInfo(map.get("fweixinId").toString(), "zh_CN");
//				TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(map.get("fweixinId").toString());
//				System.out.println(map.get("fweixinId").toString());
//				if (tSceneUser == null) {
//					if (wxMpUser.getSubscribe() == true) {
//						System.out.println("关注"+map.get("fweixinId").toString());
//
//						// 增加地推用户
//
//						TSceneUser tSceneUserAdd = new TSceneUser();
//
//						tSceneUserAdd.setFsceneId(0);// 1.地推 2.物料 9积分 0.自然关注
//						tSceneUserAdd.setFsceneStr("00000000");
//						tSceneUserAdd.setFopenId(map.get("fweixinId").toString());
//						tSceneUserAdd.setFsubscribe(1);// 0.不关注 1.关注
//						tSceneUserAdd.setFunSubscribe(0);// 0.没取消关注 1.取消关注
//						tSceneUserAdd.setFdelivery(1);// 是否领奖 0.没领奖 1.领奖
//						tSceneUserAdd.setFregister(1);
//						tSceneUserAdd.setFregisterTime(new Date());
//						tSceneUserAdd.setFsubscribeTime(new Date(wxMpUser.getSubscribeTime() * 1000L));
//						tSceneUserAdd.setFbounsCustomer(0);
//						sceneUserDAO.save(tSceneUserAdd);
//					} else {
//						System.out.println("未关注");
//
//						// 增加地推用户
//						TSceneUser tSceneUserAdd = new TSceneUser();
//
//						tSceneUserAdd.setFsceneId(0);// 1.地推 2.物料 9积分 0.自然关注
//						tSceneUserAdd.setFsceneStr("00000000");
//						tSceneUserAdd.setFopenId(map.get("fweixinId").toString());
//						tSceneUserAdd.setFsubscribe(0);// 0.不关注 1.关注
//						tSceneUserAdd.setFunSubscribe(1);// 0.没取消关注 1.取消关注
//						tSceneUserAdd.setFdelivery(1);// 是否领奖 0.没领奖 1.领奖
//						tSceneUserAdd.setFregister(1);
//						tSceneUserAdd.setFregisterTime(new Date());
//						tSceneUserAdd.setFsubscribeTime(new Date());
//						if (wxMpUser.getSubscribeTime() != null) {
//							tSceneUserAdd.setFunSubscribeTime(new Date(wxMpUser.getSubscribeTime() * 1000L));
//						} else {
//							tSceneUserAdd.setFunSubscribeTime(new Date());
//						}
//						tSceneUserAdd.setFsubscribeTime(new Date());
//						tSceneUserAdd.setFbounsCustomer(0);
//						sceneUserDAO.save(tSceneUserAdd);
//					}
//				}
//				System.out.println("增加完毕" + System.currentTimeMillis());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
		List<TSceneUser> tSceneUserList = sceneUserDAO.test();
//		for (TSceneUser tSceneUser : tSceneUserList) {
//			try {
//				System.out.println(
//						tSceneUser.getId() + "//" + tSceneUser.getFsubscribe() + "//" + tSceneUser.getFunSubscribe());
//				WxMpUser wxMpUser = coreService.getUserInfo(tSceneUser.getFopenId(), "zh_CN");
//				System.out.println("公众号是否关注的状态" + wxMpUser.getSubscribe());
//				if (wxMpUser.getSubscribe() == true) {
//						sceneUserDAO.setSubscribeAgain(tSceneUser.getId(), 1, 0);// 1.关注0.取消关注 | 0.没取消关注  1.取消关注
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
//		}
//		2016-09-01 00:00:00' and '2016-09-30 23:59:59
		return tSceneUserList ;
	}
	
	
	public void testMoUpdate(String id,String openId) {
//		System.out
//				.println(id + "//" + tSceneUser.getFsubscribe() + "//" + tSceneUser.getFunSubscribe());
		WxMpUser wxMpUser = coreService.getUserInfo(openId, "zh_CN");
		System.out.println("公众号是否关注的状态" + wxMpUser.getSubscribe());

		if (wxMpUser.getSubscribe() == true) {

			sceneUserDAO.setSubscribeAgain(id, 1, 0);// 1.关注0.取消关注// 0.没取消关注// 1.取消关注
		}
	}

}
