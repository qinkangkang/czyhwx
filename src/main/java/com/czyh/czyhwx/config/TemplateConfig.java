package com.czyh.czyhwx.config;

import com.czyh.czyhwx.util.PropertiesUtil;

public class TemplateConfig {
	
	/** 退款申请通知模板消息id */
	public static final String TEMPLATEMSGID_TYPE_REFUNDAPPLICATION = PropertiesUtil.getPropertyWechat("refundApplication");

	/** 订单评价提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_ORDEREVALUATION = PropertiesUtil.getPropertyWechat("orderEvaluation");

	/** 订单未支付通知模板消息id */
	public static final String TEMPLATEMSGID_TYPE_UNPAIDORDER = PropertiesUtil.getPropertyWechat("unpaidOrder");

	/** （慎用*）到账通知模板消息id */
	public static final String TEMPLATEMSGID_TYPE_ARRIVALNOTIFICATION = PropertiesUtil.getPropertyWechat("arrivalNotification");

	/** 订单支付成功模板消息id */
	public static final String TEMPLATEMSGID_TYPE_PAYMENTSUCCESSFUL = PropertiesUtil.getPropertyWechat("paymentSuccessful");

	/** 咨询回复消息提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_CONSULTINGREPLY = PropertiesUtil.getPropertyWechat("replyConsultMsg");
	
	/** 评论回复消息提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_COMMENTREPLY = PropertiesUtil.getPropertyWechat("commenttMsg");
	
//	/** 评论回复消息提醒模板消息id */
//	public static final String TEMPLATEMSGID_TYPE_ORDERCOMMENT = PropertiesUtil.getPropertyWechat("orderComment");
	
	/** 积分到账消息提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_BOUNSARRIVAL = PropertiesUtil.getPropertyWechat("bounsArrival");
	
	
	/** 客服回复消息提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_CUSTOMERSERVICE = PropertiesUtil.getPropertyWechat("customerService");
	
	/** 奖品兑换消息提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_PRIZEEXCHANGE = PropertiesUtil.getPropertyWechat("prizeExchange");
	
	/** 活动快结束使用提醒模板消息id(砍一砍专用) */
	public static final String TEMPLATEMSGID_TYPE_BARGAININGEND = PropertiesUtil.getPropertyWechat("bargainingend");
	
	/** 库存不足提醒模板消息id(砍一砍专用) */
	public static final String TEMPLATEMSGID_TYPE_INVENTORYSHORTAGE = PropertiesUtil.getPropertyWechat("inventoryShortage");
	
	/** 参加砍一砍提醒模板消息id(砍一砍专用) */
	public static final String TEMPLATEMSGID_TYPE_JOINBARGUN = PropertiesUtil.getPropertyWechat("joinbargin");

	/** 兑奖成功提醒模板消息id(砍一砍专用) */
	public static final String TEMPLATEMSGID_TYPE_PRIZE = PropertiesUtil.getPropertyWechat("clprize");
	
	/** 送积分提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_COMPLIMENTARYPOINTS = PropertiesUtil.getPropertyWechat("complimentaryPoints");
	
	/** 新邮件提醒模板消息id */
	public static final String TEMPLATEMSGID_TYPE_NEWEMAIL = PropertiesUtil.getPropertyWechat("newemail");
	
}