package com.czyh.czyhwx.util;

import org.springside.modules.utils.PropertiesLoader;

/**
 * @name PropertiesUtil
 * @title 操作属性文件工具类
 * @desc
 * @author
 * @version
 */
public class PropertiesUtil {

	private static PropertiesLoader propertiesLoader;
	private static PropertiesLoader propertiesLoaderwechat;

	static {
		propertiesLoader = new PropertiesLoader(Constant.SYSTEM_SETTING_FILE);
		propertiesLoaderwechat = new PropertiesLoader(Constant.SYSTEM_WECHAT_FILE);
	}

	// 获取key所对应的值
	public static String getProperty(String key) {
		return propertiesLoader.getProperty(key);
	}
	
	// 获取wxkey所对应的值
	public static String getPropertyWechat(String key) {
		return propertiesLoaderwechat.getProperty(key);
	}
}