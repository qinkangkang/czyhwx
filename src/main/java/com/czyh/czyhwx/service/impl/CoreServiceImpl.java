package com.czyh.czyhwx.service.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;

import com.czyh.czyhwx.dto.WxLoginDTO;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.CoreService;
import com.czyh.czyhwx.web.handler.LogHandler;
import com.czyh.czyhwx.web.handler.MenuHandler;
import com.czyh.czyhwx.web.handler.MsgHandler;
import com.czyh.czyhwx.web.handler.SubscribeHandler;
import com.czyh.czyhwx.web.handler.UnsubscribeHandler;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 微信接口实现类
 * 
 * @author jinshengzhi
 *
 */
@Service
public class CoreServiceImpl implements CoreService {

	private static final Logger logger = LoggerFactory.getLogger(CoreServiceImpl.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private LogHandler logHandler;

	@Autowired
	private UnsubscribeHandler unsubscribeHandler;

	@Autowired
	private SubscribeHandler subscribeHandler;

	@Autowired
	private MsgHandler msgHandler;

	@Autowired
	private MenuHandler menuHandler;

	private WxMpMessageRouter router;

	@PostConstruct
	public void init() {
		this.refreshRouter();
	}

	@Override
	public void requestGet(String urlWithParams) throws IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(urlWithParams);
		httpget.addHeader("Content-Type", "text/html;charset=UTF-8");
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(50).setConnectTimeout(50)
				.setSocketTimeout(50).build();
		httpget.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpget);
		System.out.println("StatusCode -> " + response.getStatusLine().getStatusCode());

		HttpEntity entity = response.getEntity();
		String jsonStr = EntityUtils.toString(entity);
		System.out.println(jsonStr);

		httpget.releaseConnection();
	}

	@Override
	public void requestPost(String url, List<NameValuePair> params) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

		CloseableHttpResponse response = httpclient.execute(httppost);
		System.out.println(response.toString());

		HttpEntity entity = response.getEntity();
		String jsonStr = EntityUtils.toString(entity, "utf-8");
		System.out.println(jsonStr);

		httppost.releaseConnection();
	}

	@Override
	public void refreshRouter() {
		final WxMpMessageRouter newRouter = new WxMpMessageRouter(this.wxMpService);

		// 记录所有事件的日志
		newRouter.rule().handler(this.logHandler).next();

		// 关注事件
		newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE)
				.handler(this.subscribeHandler).end();

		// 取关事件
		newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_UNSUBSCRIBE)
				.handler(this.unsubscribeHandler).end();

		// 接收客服会话管理事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
		// .handler(this.kfSessionHandler).end();
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
		// .handler(this.kfSessionHandler).end();
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
		// .handler(this.kfSessionHandler).end();

		// 门店审核事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxMpEventConstants.POI_CHECK_NOTIFY)
		// .handler(this.storeCheckNotifyHandler)
		// .end();

		// 点击菜单连接事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxConsts.BUTTON_VIEW).handler(this.nullHandler).end();

		// 自定义菜单点击事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.BUTTON_CLICK)
		// .handler(this.menuHandler).end();

		// 扫码事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxConsts.EVT_SCAN).handler(this.scanHandler()).end();

		// 上报地理位置事件
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
		// .event(WxConsts.EVT_LOCATION).handler(this.locationHandler()).end();

		// 接收地理位置消息
		// newRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
		// .handler(this.locationHandler()).end();

		// 默认消息/关键字回复消息/客服转发消息
		newRouter.rule().async(false).handler(this.msgHandler).end();

		this.router = newRouter;
	}

	@Override
	public WxMpXmlOutMessage route(WxMpXmlMessage inMessage) {
		try {
			return this.router.route(inMessage);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public WxMpUser getUserInfo(String openid, String lang) {
		WxMpUser wxMpUser = null;
		try {
			wxMpUser = this.wxMpService.getUserService().userInfo(openid, lang);
		} catch (WxErrorException e) {
			this.logger.error(e.getError().toString());
		}
		return wxMpUser;
	}

	@Override
	public ResponseDTO getOAuth2UserInfo(String code, String lang) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(code)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(202);
			responseDTO.setMsg("code参数不能为空，请检查code的传递参数值！");
			return responseDTO;
		}

		WxMpOAuth2AccessToken accessToken;
		WxMpUser wxMpUser;

		try {
			accessToken = wxMpService.oauth2getAccessToken(code);
			wxMpUser = wxMpService.oauth2getUserInfo(accessToken, lang);
		} catch (WxErrorException e) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(203);
			responseDTO.setMsg(e.getError().toString());
			return responseDTO;
		}

		WxMpUser wxMpUser2 = getUserInfo(accessToken.getOpenId(), lang);

		WxLoginDTO wxLoginDTO = new WxLoginDTO();
		wxLoginDTO.setClientType(1);
		wxLoginDTO.setUnionid(wxMpUser.getUnionId());
		wxLoginDTO.setOpenid(wxMpUser.getOpenId());
		wxLoginDTO.setNickname(wxMpUser.getNickname());
		wxLoginDTO.setHeadimgurl(wxMpUser.getHeadImgUrl());
		wxLoginDTO.setCountry(wxMpUser.getCountry());
		wxLoginDTO.setProvince(wxMpUser.getProvince());
		wxLoginDTO.setCity(wxMpUser.getCity());
		if (wxMpUser.getSex().equals("男")) {
			wxLoginDTO.setSex(1);
		} else {
			wxLoginDTO.setSex(2);
		}

		wxLoginDTO.setSubscribe(wxMpUser2.getSubscribe());

		Map<String, Object> returnData = Maps.newHashMap();
		returnData.put("userInfo", wxLoginDTO);
		// returnData.put("subscribe", wxMpUser2.getSubscribe());
		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setData(returnData);

		return responseDTO;
	}

	// public boolean hasKefuOnline() {
	// try {
	// WxMpKfOnlineList kfOnlineList = this.wxMpKefuService.kfOnlineList();
	// return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
	// } catch (Exception e) {
	// this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
	// }
	//
	// return false;
	// }

	@Override
	public ResponseDTO getWechatJssdk(String url) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (StringUtils.isBlank(url)) {
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(201);
			responseDTO.setMsg("url参数不能为空，请检查url的传递参数值！");
			return responseDTO;
		}

		WxJsapiSignature wxJsapiSignature = null;

		try {
			wxJsapiSignature = wxMpService.createJsapiSignature(url);

		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		Map<String, Object> returnData = Maps.newHashMap();
		returnData.put("jssdkParam", wxJsapiSignature);
		responseDTO.setData(returnData);

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);

		return responseDTO;
	}

}