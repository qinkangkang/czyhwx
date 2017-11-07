package com.czyh.czyhwx.web.handler;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.czyh.czyhwx.dao.SceneUserDAO;
import com.czyh.czyhwx.entity.TSceneUser;
import com.czyh.czyhwx.service.CoreService;
import com.czyh.czyhwx.service.SceneUserService;
import com.czyh.czyhwx.util.Constant;
import com.czyh.czyhwx.util.WechatMessage;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 用户关注公众号Handler
 * 
 * @jinshengzhi
 */
@Component
public class SubscribeHandler extends AbstractHandler {

	@Autowired
	private CoreService coreService;

	@Autowired
	private SceneUserDAO sceneUserDAO;

	@Autowired
	private SceneUserService sceneUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		WxMpUser wxMpUser = coreService.getUserInfo(wxMessage.getFromUser(), "zh_CN");
		/*
		 * List<NameValuePair> params = new ArrayList<>(); params.add(new
		 * BasicNameValuePair("openId", wxMpUser.getOpenId())); params.add(new
		 * BasicNameValuePair("nickname", wxMpUser.getNickname()));
		 * params.add(new BasicNameValuePair("headImgUrl",
		 * wxMpUser.getHeadImgUrl()));
		 */

		// 新增用户
		TSceneUser tSceneUser = sceneUserDAO.findOneByOpenid(wxMessage.getFromUser());
		if (tSceneUser == null) {
			String sceneStr = wxMessage.getEventKey();
			if (StringUtils.isNotBlank(sceneStr)) {
				sceneStr = sceneStr.replaceAll(Constant.sceneStr, "");
			} else {
				sceneStr = "00000000";
			}
			int sceneId = 0;
			int sex = 0;
			if (wxMpUser.getSex().equals("男")) {
				sex = 1;
			} else {
				sex = 2;
			}

			StringBuilder gps = new StringBuilder();
			if (wxMessage.getLongitude() != null && wxMessage.getLongitude() != null) {
				gps.append(wxMessage.getLongitude()).append(",").append(wxMessage.getLatitude());
			}

			sceneUserService.addSceneUser(1, wxMpUser.getUnionId(), wxMessage.getFromUser(), wxMpUser.getNickname(),
					wxMpUser.getHeadImgUrl(), wxMpUser.getCountry(), wxMpUser.getProvince(), wxMpUser.getCity(), sex,
					gps.toString(), wxMessage.getCreateTime(), sceneId, sceneStr);
		} else {
			sceneUserService.updateSceneUserUnsub(tSceneUser.getFopenId());
		}

		StringBuilder subMsg = new StringBuilder();
		subMsg.append("亲爱的").append(wxMpUser.getNickname() + "\n").append("你终于宠幸我了,这里有很多便宜优质的好货等着你，更有海量优惠福利等你来翻牌！" + "\n")
				.append("回复“1”领取新人专属福利" + "\n").append("回复“2”邀请好友得好礼" + "\n").append("回复“3”关注最新优惠活动" + "\n")
				.append("回复“4”参与一起砍价0元购" + "\n").append("回复“5”即刻出发去旅游");

		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(subMsg.toString()).fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser()).build();
		logger.info("subscribeMessageHandler" + m.getContent());
		return m;
	}
}