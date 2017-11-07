package com.czyh.czyhwx.util;

public class WechatMessage {
	/** 默认回复消息 */
	private static String defaultReplyMsg;

	/** 手动关注消息 */
	private static String subscribeMsg;

	/** 扫码关注消息 */
	private static String subscribeQrcodeMsg;

	/** 再次关注消息 */
	private static String scanMsg;

	/** 客服消息 */
	private static String customerServiceMsg;
	
	/** 生成海报发送的消息 */
	private static String makePosterMsg;
	
	/** 被扫码人增加积分时分送的消息 */
	private static String pesonBounsMsg;
	
	/** 场景码最大值 */
	private static String maxPointCode;

	public static String getDefaultReplyMsg() {
		return defaultReplyMsg;
	}

	public static void setDefaultReplyMsg(String defaultReplyMsg) {
		WechatMessage.defaultReplyMsg = defaultReplyMsg;
	}

	public static String getSubscribeMsg() {
		return subscribeMsg;
	}

	public static void setSubscribeMsg(String subscribeMsg) {
		WechatMessage.subscribeMsg = subscribeMsg;
	}

	public static String getSubscribeQrcodeMsg() {
		return subscribeQrcodeMsg;
	}

	public static void setSubscribeQrcodeMsg(String subscribeQrcodeMsg) {
		WechatMessage.subscribeQrcodeMsg = subscribeQrcodeMsg;
	}

	public static String getScanMsg() {
		return scanMsg;
	}

	public static void setScanMsg(String scanMsg) {
		WechatMessage.scanMsg = scanMsg;
	}

	public static String getCustomerServiceMsg() {
		return customerServiceMsg;
	}

	public static void setCustomerServiceMsg(String customerServiceMsg) {
		WechatMessage.customerServiceMsg = customerServiceMsg;
	}

	public static String getMaxPointCode() {
		return maxPointCode;
	}

	public static void setMaxPointCode(String maxPointCode) {
		WechatMessage.maxPointCode = maxPointCode;
	}

	public static String getMakePosterMsg() {
		return makePosterMsg;
	}

	public static void setMakePosterMsg(String makePosterMsg) {
		WechatMessage.makePosterMsg = makePosterMsg;
	}

	public static String getPesonBounsMsg() {
		return pesonBounsMsg;
	}

	public static void setPesonBounsMsg(String pesonBounsMsg) {
		WechatMessage.pesonBounsMsg = pesonBounsMsg;
	}

}
