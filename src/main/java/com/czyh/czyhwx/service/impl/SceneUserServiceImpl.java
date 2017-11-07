package com.czyh.czyhwx.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.czyh.czyhwx.config.MainConfig;
import com.czyh.czyhwx.dao.CustomerDAO;
import com.czyh.czyhwx.dao.CustomerInfoDAO;
import com.czyh.czyhwx.dao.CustomerLevelDAO;
import com.czyh.czyhwx.dao.CustomerLogDAO;
import com.czyh.czyhwx.dao.CustomerTicketDAO;
import com.czyh.czyhwx.dao.SceneScanInfoDAO;
import com.czyh.czyhwx.dao.SceneUserDAO;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.entity.TCustomer;
import com.czyh.czyhwx.entity.TCustomerInfo;
import com.czyh.czyhwx.entity.TCustomerLevel;
import com.czyh.czyhwx.entity.TCustomerTicket;
import com.czyh.czyhwx.entity.TSceneScanInfo;
import com.czyh.czyhwx.entity.TSceneUser;
import com.czyh.czyhwx.service.CommonService;
import com.czyh.czyhwx.service.SceneUserService;
import com.czyh.czyhwx.util.HttpClientUtil;
import com.czyh.czyhwx.util.PropertiesUtil;
import com.czyh.czyhwx.util.bmap.BmapGpsLocationBean;
import com.czyh.czyhwx.util.bmap.LbsCloud;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
@Transactional
public class SceneUserServiceImpl implements SceneUserService {

	public static final int INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final String ALGORITHM = "SHA-1";

	private static final Logger logger = LoggerFactory.getLogger(SceneUserServiceImpl.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private SceneUserDAO sceneUserDAO;

	@Autowired
	private SceneScanInfoDAO sceneScanInfoDAO;

	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private CustomerInfoDAO customerInfoDAO;

	@Autowired
	private CustomerTicketDAO customerTicketDAO;

	@Autowired
	private CustomerLogDAO customerLogDAO;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CustomerLevelDAO customerLevelDAO;

	public void entryptPassword(TCustomer customer) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		customer.setFsalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(customer.getFpassword().getBytes(), salt, INTERATIONS);
		customer.setFpassword(Encodes.encodeHex(hashPassword));
	}

	@Override
	public ResponseDTO updateSceneUserAgain(String openid) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(102);
			responseDTO.setMsg("请输入openid！");
			return responseDTO;
		}

		try {
			TSceneUser tSceneUser = sceneUserDAO.findSnSubscribe(openid);
			if (tSceneUser != null) {
				sceneUserDAO.setSubscribeAgain(tSceneUser.getId(), 1, 0);// 1.关注0.取消关注
																			// |
																			// 0.没取消关注
																			// 1.取消关注
				responseDTO.setSuccess(true);
				responseDTO.setStatusCode(0);
				responseDTO.setMsg("修改成功");
				return responseDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseDTO;
	}

	@Override
	public ResponseDTO saveSceneInfo(String sceneCode, String openid, Integer type, Date sSTime) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(102);
			responseDTO.setMsg("请输入openid！");
			return responseDTO;
		}

		TSceneScanInfo tSceneScanInfo = new TSceneScanInfo();
		tSceneScanInfo.setFsceneCode(sceneCode);
		tSceneScanInfo.setFopenId(openid);
		tSceneScanInfo.setFscanType(type);
		tSceneScanInfo.setFsstime(sSTime);
		tSceneScanInfo.setFcreateTime(new Date());
		sceneScanInfoDAO.save(tSceneScanInfo);

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("保存成功");
		return responseDTO;
	}

	@Override
	public Map<String, Object> getSceneUser(String openid) {
		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(openid);
		Map<String, Object> returnData = Maps.newHashMap();
		if (tSceneUser == null) {// 新用户
			returnData.put("type", 1);
			returnData.put("time", new Date());
			return returnData;
		} else if (tSceneUser.getFunSubscribeTime() == null) {// 老用户
			returnData.put("type", 2);
			returnData.put("time", tSceneUser.getFsubscribeTime());
			return returnData;
		} else {// 再关用户
			returnData.put("type", 3);
			returnData.put("time", tSceneUser.getFsubscribeTime());
			return returnData;
		}

	}

	@Override
	public String getSenceUserTime(String openid) {
		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(openid);
		SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String subTime = sdfa.format(tSceneUser.getFsubscribeTime());

		return subTime;
	}

	/**
	 * 注册新用户
	 */
	public ResponseDTO addBounsQrSubscribe(Integer clientType, String unionid, String openid, String nickname,
			String logoUrl, String country, String province, String city, Integer sex, String gps, Long subscribeTime,
			Integer sceneId, String sceneStr) {

		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(10001);
			responseDTO.setMsg("openid参数不能为空，请检查openid的传递参数值！");
			return responseDTO;
		}

		Date now = new Date();
		String ticket = RandomStringUtils.randomAlphanumeric(16);
		TCustomer tCustomer = customerDAO.getByOpenIdAndUnId(unionid, openid, 1);
		if (tCustomer == null) {
			tCustomer = new TCustomer();
			tCustomer.setFname(nickname);
			tCustomer.setFpassword(PropertiesUtil.getProperty("customerInitPassword"));
			tCustomer.setFweixinId(openid);
			tCustomer.setFweixinUnionId(unionid);
			tCustomer.setFweixinName(nickname);
			StringBuilder region = new StringBuilder();
			if (StringUtils.isNotBlank(country)) {
				region.append(country).append(" - ");
			}
			if (StringUtils.isNotBlank(province)) {
				region.append(province).append(" - ");
			}
			if (StringUtils.isNotBlank(city)) {
				region.append(city);
			}
			tCustomer.setFregion(region.toString());
			tCustomer.setFphoto(logoUrl);
			tCustomer.setFsex(sex);
			tCustomer.setFstatus(1);
			tCustomer.setFtype(1);
			tCustomer.setFcreateTime(now);
			tCustomer.setFupdateTime(now);
			entryptPassword(tCustomer);
			tCustomer = customerDAO.save(tCustomer);
			TCustomerLevel tCustomerLevel = customerLevelDAO.getByLevel(2);

			try {
				// 创建用户TICKET表记录
				TCustomerTicket tCustomerTicket = new TCustomerTicket();
				tCustomerTicket.setFcreateTime(now);
				tCustomerTicket.setFupdateTime(now);
				tCustomerTicket.setFcustomerId(tCustomer.getId());
				tCustomerTicket.setFtype(clientType);
				tCustomerTicket.setFticket(ticket);
				customerTicketDAO.save(tCustomerTicket);

				// 创建用户附加信息
				TCustomerInfo tCustomerInfo = new TCustomerInfo();
				tCustomerInfo.setFcustomerId(tCustomer.getId());
				if (sceneId.intValue() == 5) {
					tCustomerInfo.setFregisterChannel("kyk");
					tCustomerInfo.setFkykFlag(1);
				} else if (sceneId.intValue() == 9) {
					tCustomerInfo.setFregisterChannel("bouns");
					tCustomerInfo.setFjfFlag(1);
				} else if (sceneId.intValue() == 1) {
					tCustomerInfo.setFregisterChannel("ditui");
				} else if (sceneId.intValue() == 0) {
					tCustomerInfo.setFregisterChannel("zr");
				}
				// tCustomerInfo.setFregisterChannelVersion("");
				tCustomerInfo.setForderNumber(0);
				tCustomerInfo.setFpayOrderNumber(0);
				tCustomerInfo.setFpayZeroOrderNumber(0);
				tCustomerInfo.setFrefundOrderNumber(0);
				tCustomerInfo.setForderTotal(BigDecimal.ZERO);
				tCustomerInfo.setFregisterTime(now);
				tCustomerInfo.setFpoint(0);
				tCustomerInfo.setFtipNumber(0);
				tCustomerInfo.setFusedPoint(0);
				tCustomerInfo.setFcreateTime(new Date());
				tCustomerInfo.setFlevel(1);
				tCustomerInfo.setFgrowthValue(0);
				tCustomerInfo.setFneedGrowthValue(tCustomerLevel.getFgrowthValue());
				customerInfoDAO.save(tCustomerInfo);
			} catch (Exception e) {
				e.printStackTrace();
				responseDTO.setSuccess(true);
				responseDTO.setStatusCode(0);
				responseDTO.setMsg("添加失败");
			}
		}

		// 增加地推用户
		try {
			TSceneUser tSceneUserAdd = new TSceneUser();

			tSceneUserAdd.setFsceneId(sceneId);// 1.地推 2.物料 9积分 0.自然关注
			tSceneUserAdd.setFsceneStr(sceneStr);
			tSceneUserAdd.setFopenId(openid);
			if (StringUtils.isNotBlank(gps)) {
				tSceneUserAdd.setFsceneGps(gps);
			}
			tSceneUserAdd.setFsubscribe(1);// 0.不关注 1.关注
			tSceneUserAdd.setFunSubscribe(0);// 0.没取消关注 1.取消关注

			if ("1".equals(sceneStr.substring(0, 1)) || "29".equals(sceneStr.substring(0, 2))) {
				tSceneUserAdd.setFdelivery(0);// 是否领奖
			} else {
				tSceneUserAdd.setFdelivery(1);// 是否领奖 0.没领奖 1.领奖
			}
			tSceneUserAdd.setFregister(1);
			tSceneUserAdd.setFregisterTime(now);
			tSceneUserAdd.setFsubscribeTime(new Date(subscribeTime * 1000L));
			if (sceneStr.equals("00000000")) {
				tSceneUserAdd.setFbounsCustomer(1);
			} else {
				tSceneUserAdd.setFbounsCustomer(0);
			}
			tSceneUserAdd.setFcreateTime(now);
			tSceneUserAdd.setFupdateTime(now);
			sceneUserDAO.save(tSceneUserAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("添加成功");
		return responseDTO;
	}

	/**
	 * 增加关注粉丝
	 */
	public ResponseDTO addSceneUser(Integer clientType, String unionid, String openid, String nickname, String logoUrl,
			String country, String province, String city, Integer sex, String gps, Long subscribeTime, Integer sceneId,
			String sceneStr) {

		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(202);
			responseDTO.setMsg("openid参数不能为空，请检查openid的传递参数值！");
			return responseDTO;
		}
		
		// 增加地推用户
		try {
			Date now = new Date();
			TSceneUser tSceneUserAdd = new TSceneUser();

			tSceneUserAdd.setFsceneId(sceneId);// 1.地推 2.物料 9积分 0.自然关注
			tSceneUserAdd.setFsceneStr(sceneStr);
			tSceneUserAdd.setFopenId(openid);
			if (StringUtils.isNotBlank(gps)) {
				tSceneUserAdd.setFsceneGps(gps);
			}
			tSceneUserAdd.setFsubscribe(1);// 0.不关注 1.关注
			tSceneUserAdd.setFunSubscribe(0);// 0.没取消关注 1.取消关注
			tSceneUserAdd.setFdelivery(0);// 是否领奖
			// if ("1".equals(sceneStr.substring(0, 1)) ||
			// "29".equals(sceneStr.substring(0, 2))) {
			// tSceneUserAdd.setFdelivery(0);// 是否领奖
			// } else {
			// tSceneUserAdd.setFdelivery(1);// 是否领奖 0.没领奖 1.领奖
			// }
			tSceneUserAdd.setFregister(1);
			tSceneUserAdd.setFregisterTime(now);
			tSceneUserAdd.setFsubscribeTime(new Date(subscribeTime * 1000L));
			if (sceneStr.equals("00000000")) {
				tSceneUserAdd.setFbounsCustomer(1);
			} else {
				tSceneUserAdd.setFbounsCustomer(0);
			}
			tSceneUserAdd.setFcreateTime(now);
			tSceneUserAdd.setFupdateTime(now);
			sceneUserDAO.save(tSceneUserAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 增加用户表 用户ticket表 用户info表
		Date now = new Date();
		String ticket = RandomStringUtils.randomAlphanumeric(16);
		TCustomer tCustomer = customerDAO.getByOpenIdAndUnId(unionid, openid, 1);
		if (tCustomer == null) {
			tCustomer = new TCustomer();
			tCustomer.setFname(nickname);
			tCustomer.setFpassword(PropertiesUtil.getProperty("customerInitPassword"));
			tCustomer.setFweixinId(openid);
			tCustomer.setFweixinUnionId(unionid);
			tCustomer.setFweixinName(nickname);
			StringBuilder region = new StringBuilder();
			if (StringUtils.isNotBlank(country)) {
				region.append(country).append(" - ");
			}
			if (StringUtils.isNotBlank(province)) {
				region.append(province).append(" - ");
			}
			if (StringUtils.isNotBlank(city)) {
				region.append(city);
			}
			tCustomer.setFregion(region.toString());
			tCustomer.setFphoto(logoUrl);
			tCustomer.setFsex(sex);
			tCustomer.setFstatus(1);
			tCustomer.setFtype(1);
			tCustomer.setFcreateTime(now);
			tCustomer.setFupdateTime(now);
			entryptPassword(tCustomer);
			tCustomer = customerDAO.save(tCustomer);
			TCustomerLevel tCustomerLevel = customerLevelDAO.getByLevel(2);

			try {
				// 创建用户TICKET表记录
				TCustomerTicket tCustomerTicket = new TCustomerTicket();
				tCustomerTicket.setFcreateTime(now);
				tCustomerTicket.setFupdateTime(now);
				tCustomerTicket.setFcustomerId(tCustomer.getId());
				tCustomerTicket.setFtype(clientType);
				tCustomerTicket.setFticket(ticket);
				customerTicketDAO.save(tCustomerTicket);

				// 创建用户扩展信息
				TCustomerInfo tCustomerInfo = new TCustomerInfo();
				tCustomerInfo.setFcustomerId(tCustomer.getId());
				if (sceneId.intValue() == 5) {
					tCustomerInfo.setFregisterChannel("kyk");
					tCustomerInfo.setFkykFlag(1);
				} else if (sceneId.intValue() == 9) {
					tCustomerInfo.setFregisterChannel("bouns");
					tCustomerInfo.setFjfFlag(1);
				} else if (sceneId.intValue() == 1) {
					tCustomerInfo.setFregisterChannel("ditui");
				} else if (sceneId.intValue() == 0) {
					tCustomerInfo.setFregisterChannel("zr");
				}
				// tCustomerInfo.setFregisterChannelVersion("");
				tCustomerInfo.setForderNumber(0);
				tCustomerInfo.setFpayOrderNumber(0);
				tCustomerInfo.setFpayZeroOrderNumber(0);
				tCustomerInfo.setFrefundOrderNumber(0);
				tCustomerInfo.setForderTotal(BigDecimal.ZERO);
				tCustomerInfo.setFregisterTime(now);
				tCustomerInfo.setFpoint(0);
				tCustomerInfo.setFtipNumber(0);
				tCustomerInfo.setFusedPoint(0);
				tCustomerInfo.setFcreateTime(new Date());
				tCustomerInfo.setFlevel(1);
				tCustomerInfo.setFgrowthValue(0);
				tCustomerInfo.setFneedGrowthValue(tCustomerLevel.getFgrowthValue());
				customerInfoDAO.save(tCustomerInfo);
			} catch (Exception e) {
				e.printStackTrace();
				responseDTO.setSuccess(true);
				responseDTO.setStatusCode(0);
				responseDTO.setMsg("添加用户基础信息失败");
			}
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("添加用户基础信息成功");
		return responseDTO;
	}

	/**
	 * 判断用户是否领过奖品
	 * 
	 * @param qrcodeUrl
	 * @param headUrl
	 * @return
	 */
	public String getSceneUserExist(String openid) {

		if (StringUtils.isBlank(openid)) {
			logger.info("openid不能为空");
		}

		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(openid);

		if (tSceneUser != null && tSceneUser.getFdelivery() == 0) {
			return "0";// 当前用户为新用户可以进行领奖
		} else {
			return "1";// 当前用户已经扫过地推码不能在领奖了
		}

	}

	/**
	 * 判断用户属于那个渠道
	 */
	public String getChannelType(String openid) {

		if (StringUtils.isBlank(openid)) {
			logger.info("openid参数不能为空，请检查openid的传递参数值！");
		}

		TSceneUser sceneUser = sceneUserDAO.findOneByOpenid(openid);

		String channelType = null;
		if (StringUtils.isNotBlank(sceneUser.getFsceneStr())) {

			if (sceneUser.getFsceneStr().substring(0, 2).equals("10")) {

				channelType = "1";
				return channelType;// 当前用户为递推渠道

			} else if (sceneUser.getFsceneStr().substring(0, 2).equals("20")) {

				channelType = "2";
				return channelType;// 当前用户为物料渠道
			} else if (sceneUser.getFsceneStr().substring(0, 1).equals("9")) {

				channelType = "9";
				return channelType;// 当前用户为积分渠道
			}
		} else {
			channelType = "999";
			return channelType;// 当前用户无渠道
		}
		return channelType;

	}

	/**
	 * 获取用户附加信息判断该用户是否生成过海报
	 * 
	 * @param qrcodeUrl
	 * @param headUrl
	 * @return
	 */
	public Map<String, String> getUserInfoWxPoster(String openid) {
		Map<String, String> returnData = Maps.newHashMap();

		TCustomer tCustomer = customerDAO.findOneByOpenId(openid);

		if (tCustomer != null) {
			TCustomerInfo tCustomerInfo = customerInfoDAO.getByCustomerId(tCustomer.getId());
			if (tCustomerInfo.getFinvalidTime() == null && tCustomerInfo.getFposterImage() == null) {
				returnData.put("invalidTime", "1");// 如果为空的话就回1代表
				returnData.put("posterImage", "1");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				returnData.put("invalidTime", sdf.format(tCustomerInfo.getFinvalidTime()));
				returnData.put("posterImage", tCustomerInfo.getFposterImage());
				returnData.put("qRcodeImage", tCustomerInfo.getFqRcodeImage());
			}

		}
		return returnData;
	}

	/**
	 * 获取用户附加信息表场景码当前最大值
	 * 
	 * @param qrcodeUrl
	 * @param headUrl
	 * @return
	 */
	public String getMAXPointCode() {

		String pointCodeNum = customerInfoDAO.getMaxPointCode();
		String codeNum;
		if (pointCodeNum != null) {
			codeNum = pointCodeNum;
			return codeNum;
		} else {
			codeNum = "90000000";
			return codeNum;

		}
	}

	/**
	 * 修改用户附加信息
	 * 
	 * @param qrcodeUrl
	 * @param headUrl
	 * @return
	 */
	public ResponseDTO updateUserInfoWxPoster(String openid, String fpointCode, Date finvalidTime) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(202);
			responseDTO.setMsg("openId参数不能为空，请检查openId的传递参数值！");
			return responseDTO;
		}

		TCustomer tCustomer = customerDAO.findOneByOpenId(openid);

		if (tCustomer != null) {
			customerInfoDAO.updatePosterInfo(tCustomer.getId(), fpointCode, finvalidTime);
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		return responseDTO;
	}

	public ResponseDTO setSceneGPS(String openid, String gps) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(101);
			responseDTO.setMsg("请输入openID！");
			return responseDTO;
		}
		if (StringUtils.isBlank(gps)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(102);
			responseDTO.setMsg("请输入GPS！");
			return responseDTO;
		}

		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(openid);

		if (tSceneUser != null) {
			if (StringUtils.isBlank(tSceneUser.getFsceneGps())) {
				sceneUserDAO.saveGps(gps, tSceneUser.getId());
			}
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		return responseDTO;
	}

	public String getQrcode(String openid) {

		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(openid);

		if (tSceneUser != null) {
			String qrcode = tSceneUser.getFsceneStr();
			return qrcode;

		} else {
			return "999";
		}

	}

	public ResponseDTO updateSceneStr(String openid, String str) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(202);
			responseDTO.setMsg("openId参数不能为空，请检查openId的传递参数值！");
			return responseDTO;
		}

		if (StringUtils.isBlank(str)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(202);
			responseDTO.setMsg("str参数不能为空，请检查str的传递参数值！");
			return responseDTO;
		}

		try {
			sceneUserDAO.setfSceneStr(openid, str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		return responseDTO;
	}

	public String findByCustomerInfo(String openid) {

		String qrcodeNum = null;
		try {
			TCustomer tCustomer = customerDAO.findOneByOpenId(openid);

			TCustomerInfo tCustomerInfo = customerInfoDAO.getByCustomerId(tCustomer.getId());

			qrcodeNum = tCustomerInfo.getFpointCode();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return qrcodeNum;
	}

	/**
	 * 粉丝取消关注
	 */
	public ResponseDTO updateSceneUser(String openid, Long subscribeTime) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(10010);
			responseDTO.setMsg("请输入openid！");
			return responseDTO;
		}

		TSceneUser tSceneUser = sceneUserDAO.findSceneUserSub(openid);
		if (tSceneUser == null) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(10011);
			responseDTO.setMsg("该用户已经取消关注请勿重复取消关注");
			return responseDTO;
		}

		sceneUserDAO.setUnSubscribe(tSceneUser.getId(), 0, 1, new Date(subscribeTime * 1000L));
		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		return responseDTO;
	}

	/**
	 * 用户关注
	 */
	public ResponseDTO updateSceneUserUnsub(String openid) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (StringUtils.isBlank(openid)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(10010);
			responseDTO.setMsg("请输入openid！");
			return responseDTO;
		}

		TSceneUser tSceneUser = sceneUserDAO.findSceneUserUnSub(openid);
		if (tSceneUser == null) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(10011);
			responseDTO.setMsg("该用户已经关注微信公众号");
			System.out.println("已经关注了");
			return responseDTO;
		}

		sceneUserDAO.setSubscribeAgain(openid, 1, 0);
		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		return responseDTO;
	}

	/**
	 * 修改位置
	 * 
	 * @param openid
	 * @return
	 */
	public void updateCityName(String openid, String gps) {

		// 用gps换取地理未知信息
		LinkedHashMap<String, Object> paramsMap = Maps.newLinkedHashMap();
		if (StringUtils.isNotBlank(gps)) {
			try {
				paramsMap.put("ak", LbsCloud.AK);
				// paramsMap.put("callback", "renderReverse");
				String[] gpsa = StringUtils.split(gps, ',');
				paramsMap.put("location", new StringBuilder().append(gpsa[1]).append(",").append(gpsa[0]).toString());
				paramsMap.put("output", "json");
				paramsMap.put("pois", "0");

				String sn = LbsCloud.getSn(LbsCloud.LOCATION_URI_GPS, paramsMap);
				paramsMap.put("sn", sn);

				String json = HttpClientUtil.callUrlGet(LbsCloud.LBS_URL + LbsCloud.LOCATION_URI_GPS, paramsMap);
				BmapGpsLocationBean bmapGpsLocationBean = mapper.fromJson(json, BmapGpsLocationBean.class);
				// 返回状态码如果为0，表示获取活动距离成功
				if (bmapGpsLocationBean.getStatus() == 0) {
					sceneUserDAO.updateCityName(openid, bmapGpsLocationBean.getResult().getAddressComponent().getCity(),
							bmapGpsLocationBean.getResult().getFormatted_address());
				}
			} catch (Exception e) {
				logger.error("去百度LBS云坐标信息获取城市码时出错");
			}
		}
	}

	public void test2() {
		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();

		StringBuilder hql = new StringBuilder();

		hql.append("select t.fopenId as fopenId from TSceneUser t where t.fsubscribe = 1 order by fcreateTime desc");

		Map<String, Object> hqlMap = new HashMap<String, Object>();

		List<Map<String, Object>> list = commonService.find(hql.toString(), hqlMap);

		WxMpUser user = null;
		for (Map<String, Object> amap : list) {
			try {
				user = wxMpService.getUserService().userInfo(amap.get("fopenId").toString(), "zh_CN");

				byte[] btImg = this.getImageFromNetByUrl(user.getHeadImgUrl());
				if (null != btImg && btImg.length > 0) {
					logger.warn("读取到：" + btImg.length + " 字节");
					String fileName = amap.get("fopenId").toString();
					// fileName =
					// fileName.replace(fileName.charAt(fileName.length() - 1) +
					// "", "64");
					String name = user.getNickname();
					writeImageToDisk(btImg, fileName, name);
				} else {
					logger.warn("当前用户头像的连接为空");
				}

			} catch (WxErrorException e) {
				e.printStackTrace();
			}
			logger.warn("当前用户头像为：" + user.getHeadImgUrl());
		}

	}

	public static void writeImageToDisk(byte[] img, String fileName, String name) {
		try {
			File file = new File("F:\\wechatImage2\\" + name + "(" + fileName + ")" + ".jpg");
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			logger.warn("图片已经写入到F盘wechatImage内");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据地址获得数据的字节流
	 * 
	 * @param strUrl
	 *            网络连接地址
	 * @return
	 */
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
