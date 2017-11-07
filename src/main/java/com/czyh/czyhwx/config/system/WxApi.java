package com.czyh.czyhwx.config.system;

import com.czyh.czyhwx.util.PropertiesUtil;

public class WxApi {

	/** 微信-加密签名 */
	public static final String WX_SIGNATURE = "signature";

	/** 微信-时间戳 */
	public static final String WX_TIMESTAMP = "timestamp";

	/** 微信-随机数 */
	public static final String WX_NONCE = "nonce";

	/** 微信-随机字符串 */
	public static final String WX_ECHOSTR = "echostr";

	/** 微信-wx相关api */
	public static final String WX_SERVICEURL = "serviceUrl";

	/** 微信-wx支付用api */
	public static final String ENTERPRISE_PAY_URL = PropertiesUtil.getPropertyWechat("enterprisePayUrl");

	/** 微信-wx证书p12位置 */
	public static final String LOAD_IDENTIFICATION = PropertiesUtil.getPropertyWechat("load_identification");

	/** 微信-wx证书h5_rootca位置 */
	public static final String LOAD_TRUSTS = "load_truts";

	/** 微信-Bouns(积分宝)相关api */
	public static final String Bouns_SERVICEURL = "bounsUrl";

	/** 微信-WxLogin(微信登录)相关api */
	public static final String WxLogin_SERVICEURL = "wxLoginUrl";

	/** czyhInterface-api-获取用户渠道类型接口 */
	public static final String WX_API_GetChannelType = "getChannelType";

	/** czyhInterface-api-地推用户取消关注接口 */
	public static final String WX_API_UpdateSceneUser = "updateSceneUser";

	/** czyhInterface-api-获取用户附加信息接口 */
	public static final String WX_API_GetUserInfoAndWxPoster = "getUserInfoAndWxPoster";

	/** czyhInterface-api-修改用户附加信息接口 */
	public static final String WX_API_UpdateUserInfoWxPoster = "updateUserInfoWxPoster";

	/** czyhInterface-api-制作海报接口 */
	public static final String WX_API_GetPosterImage = "getPosterImage";

	/** czyhInterface-api-设置用户当前Gps接口 */
	public static final String WX_API_SetSceneGPS = "setSceneGPS";

	/** czyhInterface-api-保存递推用户信息接口 */
	public static final String WX_API_SaveSceneUser = "saveSceneUser";

	/** czyhInterface-api-判断该用户是否存在息接口(积分专用) */
	public static final String WX_API_GetOpenIdToCustomerId = "getOpenIdToCustomerId";

	/** czyhInterface-api-注册场景为积分的用户接口 */
	public static final String WX_API_AddBounsQrSubscribe = "addBounsQrSubscribe";

	/** czyhInterface-api-判断该用户是否存在息接口(递推专用) */
	public static final String WX_API_GetSceneUserExist = "getSceneUserExist";

	/** czyhInterface-api-获取用户表最大场景码 */
	public static final String WX_API_GetMAXPointCode = "getMAXPointCode";

	/** czyhInterface-api-获取被关注的人 */
	public static final String WX_API_GetPosterSUser = "getPosterSUser";

	/** czyhInterface-api-关注时增加积分接口 (积分用) */
	public static final String WX_API_AddBonusAttention = "addBonusAttention";

	/** czyhInterface-api-授权位置时增加积分接口 (积分用) */
	public static final String WX_API_AddBonusInvitation = "addBonusInvitation";

	/** czyhInterface-api-取消关注时增加积分接口 (积分用) */
	public static final String WX_API_ReduceBonusCancel = "reduceBonusCancel";

	/** czyhInterface-api-微信登录接口 (微信登录用) */
	public static final String WX_API_WxLogin = "wxLogin";

	/** czyhInterface-api-砍一砍url (砍一砍活动用) */
	public static final String KYKURL = "bargainingUrl";

	/** czyhInterface-api-嘉年华url (嘉年华活动用) */
	public static final String CARNUVALURL = "carnivalUrl";

}