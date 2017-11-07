package com.czyh.czyhwx.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

import com.czyh.czyhwx.dto.m.ResponseDTO;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 微信接口
 * 
 * @author jinshengzhi
 *
 */
public interface CoreService {

	/**
	 * HttpGet请求
	 *
	 * @param urlWithParams
	 * @throws Exception
	 */
	void requestGet(String urlWithParams) throws IOException;

	/**
	 * HttpPost请求
	 *
	 * @param url
	 * @param params
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException;

	/**
	 * 刷新消息路由器
	 */
	void refreshRouter();

	/**
	 * 路由消息
	 *
	 * @param inMessage
	 * @return
	 */
	WxMpXmlOutMessage route(WxMpXmlMessage inMessage);

	/**
	 * 通过openid获得基本用户信息
	 *
	 * @param openid
	 * @param lang
	 * @return
	 */
	WxMpUser getUserInfo(String openid, String lang);

	/**
	 * 微信code授权登录
	 * 
	 * @param code
	 * @param lang
	 * @return
	 */
	public ResponseDTO getOAuth2UserInfo(String code, String lang);

	// public void SubscribeClick(WxMpXmlMessage wxMessage);
	
	/**
	 * getWechatJssdk
	 * 
	 * @param code
	 * @param lang
	 * @return
	 */
	public ResponseDTO getWechatJssdk(String url) throws WxErrorException;

	

}
