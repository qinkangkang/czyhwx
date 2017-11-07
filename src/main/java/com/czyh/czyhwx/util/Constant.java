package com.czyh.czyhwx.util;

import java.util.Map;

import org.apache.commons.lang3.SystemUtils;

public class Constant {

	// 系统属性设置文件
	public static final String SYSTEM_SETTING_FILE = "classpath:/system.properties";
	
	// 微信系统属性设置文件
	public static final String SYSTEM_WECHAT_FILE = "classpath:/wechat.properties";

	// 项目文件根路径
	public static String RootPath = null;

	// 系统默认的语言
	public static final String defaultLanguage = "zh_CN";

	// 英文语言
	public static final String englishLanguage = "en_US";

	// 默认头像
	public static String defaultHeadImgUrl = null;

	// 移动端活动详情页URL
	public static String czyhInterfaceServiceUrl = null;

	// 微信push接口URL
	public static String weChatPushUrl = null;

	// 微信模板消息first内容
	public static String consultingReplyFirst = null;

	// 微信模板消息备注内容
	public static String consultingReplyRemark = null;

	// 微信模板消息first内容
	public static String commentingReplyFirst = null;

	// 微信模板消息备注内容
	public static String commentingReplyRemark = null;

	// 微信订单评价提醒模板消息first内容
	public static String orderEvaluationFirst = null;

	// 微信订单评价提醒模板消息备注内容
	public static String orderEvaluationRemark = null;

	// 前端个人用户订单状态页面
	public static String orderStatusUrl = null;

	// 前端咨询页面地址
	public static String consultUrl = null;
	
	// 替换二维码前缀
	public static final String sceneStr = "qrscene_";

	// APP栏目MAP
	public static Map<String, String> appChannelMap = null;

	public static void init() {
		if (SystemUtils.IS_OS_WINDOWS) {
			RootPath = PropertiesUtil.getProperty("windowsRootPath");
		} else if (SystemUtils.IS_OS_MAC_OSX) {
			RootPath = PropertiesUtil.getProperty("macRootPath");
		} else {
			RootPath = PropertiesUtil.getProperty("linuxRootPath");
		}

	}

}