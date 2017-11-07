package com.czyh.czyhwx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czyh.czyhwx.config.TemplateConfig;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.CommonService;
import com.czyh.czyhwx.service.CoreService;
import com.czyh.czyhwx.service.TemplateMessageService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
@Transactional(readOnly = true)
public class TemplateMessageServiceImpl implements TemplateMessageService {

	private static final Logger logger = LoggerFactory.getLogger(TemplateMessageServiceImpl.class);

	// 模板消息字体颜色
	// private static final String TEMPLATE_FRONT_COLOR = "#32CD32";
	private static final String TEMPLATE_FRONT_COLOR = "";

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CoreService coreService;

	@Override
	public ResponseDTO notifyOrderPaySuccessTemplate(String openid, String first, String orderMoneySum,
			String orderProductName, String orderName, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_PAYMENTSUCCESSFUL);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData orderMoneySumData = new WxMpTemplateData("orderMoneySum", orderMoneySum, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData orderProductNameData = new WxMpTemplateData("orderProductName", orderProductName,
				TEMPLATE_FRONT_COLOR);
		WxMpTemplateData orderNameData = new WxMpTemplateData("orderName", orderName, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData remarkData = new WxMpTemplateData("Remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(orderMoneySumData);
		wxMessageTemplate.addWxMpTemplateData(orderProductNameData);
		wxMessageTemplate.addWxMpTemplateData(orderNameData);
		wxMessageTemplate.addWxMpTemplateData(remarkData);
		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("支付成功消息发送成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyRefundTemplate(String openid, String first, String refundMoneySum,
			String refundProductName, String refundName, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_REFUNDAPPLICATION);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData refundMoneySumData = new WxMpTemplateData("refundMoneySum", refundMoneySum,
				TEMPLATE_FRONT_COLOR);
		WxMpTemplateData refundProductNameData = new WxMpTemplateData("refundProductName", refundProductName,
				TEMPLATE_FRONT_COLOR);
		WxMpTemplateData refundNameData = new WxMpTemplateData("refundName", refundName, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(refundMoneySumData);
		wxMessageTemplate.addWxMpTemplateData(refundProductNameData);
		wxMessageTemplate.addWxMpTemplateData(refundNameData);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("退款申请通知消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyOrderUnPayTemplate(String openid, String first, String ordertape, String ordeID,
			String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_UNPAIDORDER);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData ordertapeData = new WxMpTemplateData("ordertape", ordertape, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData ordeIDData = new WxMpTemplateData("ordeID", ordeID, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(ordertapeData);
		wxMessageTemplate.addWxMpTemplateData(ordeIDData);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送订单未支付提醒消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyOrderEvaluationTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_ORDEREVALUATION);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送订单评价提醒消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyReplyConsultTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_CONSULTINGREPLY);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送咨询回复消息提消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyCommentConsultTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_COMMENTREPLY);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送评论回复消息提消息成功");
		return responseDTO;
	}

	/**
	 * 到账提醒*慎用
	 */
	@Override
	public ResponseDTO notifyArrivalTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_COMMENTREPLY);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送到账消息提消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyBounsTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String keyword5, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_BOUNSARRIVAL);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword4Data = new WxMpTemplateData("keyword4", keyword4, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword5Data = new WxMpTemplateData("keyword5", keyword5, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(keyword4Data);
		wxMessageTemplate.addWxMpTemplateData(keyword5Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("积分到账消息发送成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyBounsListTemplate() {

		ResponseDTO responseDTO = new ResponseDTO();

		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder();
		hql.append(
				"select t.fweixinId as wxid,b.fpoint as bus, t.fcreateTime as fcreateTime from TCustomer  t inner join TCustomerInfo b on t.id = b.fcustomerId")
				.append(" inner join TSceneUser c on t.fweixinId = c.fopenId")
				.append(" where c.fsubscribe=1 and t.ftype = 1 and t.fstatus = 1 and t.fcreateTime < '2016-05-20 21:24:24' and t.fweixinId is not null order by t.fcreateTime desc");
		List<Map<String, Object>> list = commonService.find(hql.toString(), hqlMap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String first = "中秋发福利啦～零到壹送您的积分已经发送到您的账户，请查收！";
		String keyword2 = sdf.format(date);
		String keyword3 = "活动赠送";
		String keyword4 = "5分";
		String remark = "点击查看账户详情，积分商城有小米平衡车哟，手慢就晚了快来抢！";
		String url = "http://m.fangxuele.com/t/#/profile-bonus";

		for (Map<String, Object> amap : list) {
			try {
				WxMpUser wxMpUser = coreService.getUserInfo(amap.get("wxid").toString(), "zh_CN");

				if (wxMpUser != null) {
					WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
					wxMessageTemplate.setToUser(amap.get("wxid").toString());
					wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_BOUNSARRIVAL);
					wxMessageTemplate.setUrl(url);
					WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);

					WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", wxMpUser.getNickname(),
							TEMPLATE_FRONT_COLOR);
					WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
					WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);
					WxMpTemplateData keyword4Data = new WxMpTemplateData("keyword4", keyword4, TEMPLATE_FRONT_COLOR);
					WxMpTemplateData keyword5Data = new WxMpTemplateData("keyword5", amap.get("bus").toString(),
							TEMPLATE_FRONT_COLOR);

					WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
					wxMessageTemplate.addWxMpTemplateData(firstData);
					wxMessageTemplate.addWxMpTemplateData(keyword1Data);
					wxMessageTemplate.addWxMpTemplateData(keyword2Data);
					wxMessageTemplate.addWxMpTemplateData(keyword3Data);
					wxMessageTemplate.addWxMpTemplateData(keyword4Data);
					wxMessageTemplate.addWxMpTemplateData(keyword5Data);
					wxMessageTemplate.addWxMpTemplateData(remarkData);

					wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
				}
			} catch (WxErrorException e) {
				logger.error("发送失败");
			}
			logger.warn(
					"当前发送用户的创建时间是：" + DateFormatUtils.format((Date) amap.get("fcreateTime"), "yyyy-MM-dd HH:mm:ss"));
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("积分到账消息发送成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyOrderCommentTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		// wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_ORDERCOMMENT);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送到账消息提消息成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyCustomerServiceTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_CUSTOMERSERVICE);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送客服回复提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyPrizeExchangeTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_PRIZEEXCHANGE);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword4Data = new WxMpTemplateData("keyword4", keyword4, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(keyword4Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发奖品兑换提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyBargainingEndTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_BARGAININGEND);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送砍一砍活动快结束提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyInventoryShortageTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_INVENTORYSHORTAGE);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送砍一砍库存不足提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyJoinTemplate(String openid, String first, String keyword1, String keyword2, String remark,
			String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_JOINBARGUN);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送砍一砍开始提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyPrizeTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_PRIZE);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword3Data = new WxMpTemplateData("keyword3", keyword3, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword4Data = new WxMpTemplateData("keyword4", keyword4, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(keyword3Data);
		wxMessageTemplate.addWxMpTemplateData(keyword4Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送奖品兑换成功提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyComplimentaryPointsTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url) {

		ResponseDTO responseDTO = new ResponseDTO();

		WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
		wxMessageTemplate.setToUser(openid);
		wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_COMPLIMENTARYPOINTS);
		wxMessageTemplate.setUrl(url);
		WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword1Data = new WxMpTemplateData("keyword1", keyword1, TEMPLATE_FRONT_COLOR);
		WxMpTemplateData keyword2Data = new WxMpTemplateData("keyword2", keyword2, TEMPLATE_FRONT_COLOR);

		WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
		wxMessageTemplate.addWxMpTemplateData(firstData);
		wxMessageTemplate.addWxMpTemplateData(keyword1Data);
		wxMessageTemplate.addWxMpTemplateData(keyword2Data);
		wxMessageTemplate.addWxMpTemplateData(remarkData);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);
		} catch (WxErrorException e) {
			logger.error(e.getMessage().toString());
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getMessage().toString());
			return responseDTO;
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送评论返现提醒成功");
		return responseDTO;
	}

	@Override
	public ResponseDTO notifyNewEmailTemplate() {

		ResponseDTO responseDTO = new ResponseDTO();

		Map<String, Object> hqlMap = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder();
		hql.append(
				"select t.fweixinId as openid from TCustomer t inner join TCustomerInfo i on t.id = i.fcustomerId inner join TSceneUser u on u.fopenId = t.fweixinId ")
				.append(" where (i.fpoint between 0 and 100) and (u.fsubscribeTime between '2016-9-15 00:00:00' and '2016-9-30 23:59:59') and u.fsubscribe=1");
		List<Map<String, Object>> list = commonService.find(hql.toString(), hqlMap);

		int i = 1;
		int n = 1;
		for (Map<String, Object> map : list) {
			try {
				String first = "亲，您有免费的3M儿童口罩可以领取呦";
				// String keyword1 = ;
				// String keyword2 = ;
				String remark = "点击查看详情";
				String url = "http://m.fangxuele.com/t/#/profile-bonus";

				WxMpTemplateMessage wxMessageTemplate = new WxMpTemplateMessage();
				wxMessageTemplate.setToUser(map.get("openid").toString());
				wxMessageTemplate.setTemplateId(TemplateConfig.TEMPLATEMSGID_TYPE_NEWEMAIL);
				wxMessageTemplate.setUrl(url);
				WxMpTemplateData firstData = new WxMpTemplateData("first", first, TEMPLATE_FRONT_COLOR);
				WxMpTemplateData keyword1Data = new WxMpTemplateData("subject", "积分福利", TEMPLATE_FRONT_COLOR);
				WxMpTemplateData keyword2Data = new WxMpTemplateData("sender", "零到壹\n消耗积分：50积分\n兑换商品：3M儿童口罩2只装，包邮哟~",
						TEMPLATE_FRONT_COLOR);

				WxMpTemplateData remarkData = new WxMpTemplateData("remark", remark, TEMPLATE_FRONT_COLOR);
				wxMessageTemplate.addWxMpTemplateData(firstData);
				wxMessageTemplate.addWxMpTemplateData(keyword1Data);
				wxMessageTemplate.addWxMpTemplateData(keyword2Data);
				wxMessageTemplate.addWxMpTemplateData(remarkData);

				wxMpService.getTemplateMsgService().sendTemplateMsg(wxMessageTemplate);

				logger.warn("已发送人数" + i++);
			} catch (WxErrorException e) {

				logger.warn("发送失败人的openid" + map.get("openid").toString() + "发送失败人数：" + n++);
			}
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("发送新邮件提醒成功");
		return responseDTO;
	}
}