package com.czyh.czyhwx.web.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * 默认消息回复
 * 
 * @author jinshengzhi
 *
 */
@Component
public class MsgHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {

		StringBuilder subMsg = new StringBuilder();

		if ("5".equals(wxMessage.getContent())) {
			subMsg.append("一样的旅行，一半的价格，优选即刻出发" + "\n").append("<a href='")
					.append("http://www.jikechufa.com/Mobile/Index.html'>【点击即刻出发】</a>");
		} else if ("轰趴集结号".equals(wxMessage.getContent())) {
			subMsg.append("爆款桌游，海量游戏，ktv、台球、棋牌、桌面足球，高颜值帅哥美女，仅需18元，高逼格轰趴馆畅玩！！！").append("").append("<a href='").append(
					"http://czyhsd.021-sdeals.cn/sdeals/#/event-basic?id=baa1caf7-e62e-40ad-b9cc-16f2d12a40df'>【点此去嗨！！！】</a>");
		} else if ("1".equals(wxMessage.getContent())) {
			subMsg.append("颜值美的都来领取了").append("").append("<a href='")
					.append("http://czyhsd.021-sdeals.cn/sdeals/#/invite'>【点击领取新人礼包】</a>");
		} else if ("2".equals(wxMessage.getContent())) {
			subMsg.append("邀请好友下首单，优惠福利拿不完").append("").append("<a href='")
					.append("http://czyhsd.021-sdeals.cn/sdeals/#/profile-invite'>【点击邀请好友得优惠券】</a>");
		} else if ("3".equals(wxMessage.getContent())) {
			subMsg.append("内容：来呀，快活呀！惊艳全场你买我送！").append("").append("<a href='")
					.append("http://mp.weixin.qq.com/s/Z9q2P9lg8HvW57lz3gjP2A'>【点击查看最新优惠活动】</a>");
		} else if ("4".equals(wxMessage.getContent())) {
			subMsg.append("呼朋唤友，砍！砍！砍！低至零元哦！").append("").append("<a href='")
					.append("http://czyhsd.021-sdeals.cn/sdeals/#/event-bargain'>【点此去砍】</a>");
		} else {
			subMsg.append("被召唤好鸡冻！有任何问题都可在后台回复我们哦，优惠小客服会第一时间为您解决~");
		}

		WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(subMsg.toString()).fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser()).build();
		logger.info("msgHandler" + m.getContent());
		return m;

	}
}