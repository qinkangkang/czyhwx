package com.czyh.czyhwx.web.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.czyh.czyhwx.service.CoreService;
import com.czyh.czyhwx.service.SceneUserService;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Component
public class UnsubscribeHandler extends AbstractHandler {

	@Autowired
	private CoreService coreService;

	@Autowired
	private SceneUserService sceneUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		WxMpUser wxMpUser = coreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");

		// 更新用户关注状态
		sceneUserService.updateSceneUser(wxMessage.getFromUser(), wxMessage.getCreateTime());
		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("").build();
		return m;
	}

}