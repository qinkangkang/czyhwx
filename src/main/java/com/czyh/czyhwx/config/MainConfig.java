package com.czyh.czyhwx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.czyh.czyhwx.util.PropertiesUtil;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 微信基础信息配置
 * 
 * @author jinshengzhi
 *
 */
@Configuration
public class MainConfig {

	// 零到壹微信订阅公众号 APP_ID
	protected static final String APP_ID = PropertiesUtil.getPropertyWechat("appId");
	// 零到壹微信公订阅众号 APP_SECRET
	protected static final String APP_SECRET = PropertiesUtil.getPropertyWechat("secret");
	// 零到壹微信公订阅众号 TOKEN
	protected static final String TOKEN = PropertiesUtil.getPropertyWechat("token");
	// 零到壹微信公订阅众号 AES_KEY
	protected static final String AES_KEY = PropertiesUtil.getPropertyWechat("aesKey");

	// 零到壹微信支付商户号
	protected static final String PARTNER_ID = PropertiesUtil.getPropertyWechat("partnerId");
	// 零到壹微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
	protected static final String PARTNER_KEY = PropertiesUtil.getPropertyWechat("partnerKey");

	// 零到壹微信服务公众号 APP_ID
	protected static final String APP_ID_Test = PropertiesUtil.getPropertyWechat("test_appId");
	// 零到壹微信服务公众号 APP_SECRET
	protected static final String APP_SECRET_Test = PropertiesUtil.getPropertyWechat("test_secret");
	// 零到壹微信服务公众号 TOKEN
	protected static final String TOKEN_Test = PropertiesUtil.getPropertyWechat("test_token");
	// 零到壹微信服务公众号 AES_KEY
	protected static final String AES_KEY_Test = PropertiesUtil.getPropertyWechat("test_aesKey");

	// 零到壹微信支付商户号
	protected static final String PARTNER_ID_Test = PropertiesUtil.getPropertyWechat("test_partnerId");
	// 零到壹微信支付平台商户API密钥(https://pay.weixin.qq.com/index.php/core/account/api_cert)
	protected static final String PARTNER_KEY_Test = PropertiesUtil.getPropertyWechat("test_partnerKey");

	 @Bean
	 public WxMpConfigStorage wxMpConfigStorage() {
		 WxMpInMemoryConfigStorage configStorage = new
		 WxMpInMemoryConfigStorage();
		 configStorage.setAppId(MainConfig.APP_ID);
		 configStorage.setSecret(MainConfig.APP_SECRET);
		 configStorage.setToken(MainConfig.TOKEN);
		 configStorage.setAesKey(MainConfig.AES_KEY);
	//	 configStorage.setPartnerId(MainConfig.PARTNER_ID);
	//	 configStorage.setPartnerKey(MainConfig.PARTNER_KEY);
	 return configStorage;
	 }

//	@Bean
//	public WxMpConfigStorage wxMpConfigStorage() {
//		WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
//		configStorage.setAppId(MainConfig.APP_ID_Test);
//		configStorage.setSecret(MainConfig.APP_SECRET_Test);
//		configStorage.setToken(MainConfig.TOKEN_Test);
//		configStorage.setAesKey(MainConfig.AES_KEY_Test);
//		return configStorage;
//	}

	@Bean
	public WxMpService wxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}

}
