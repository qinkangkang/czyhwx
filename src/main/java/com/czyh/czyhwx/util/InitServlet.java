package com.czyh.czyhwx.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个初始化的servlet类，在应用启动的时候，做一些初始化的工作。
 */
public class InitServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(InitServlet.class);

	private static final long serialVersionUID = 1L;

	/** 地推领奖链接 */
	private static final String GIFTURL = "giftUrl";
	


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		HttpClientUtil.init();
		logger.warn("Http客户端类初始化完成！");

		// 默认回复的消息
		StringBuilder defaultContent = new StringBuilder();
		defaultContent.append("hi~你来了[玫瑰]\n \n 活动有疑问请咨询零到壹官方客服微信号哦~ 小安@零到壹：18613183438或imxiaoan \n \n ")
				.append("订单查询：<a href='").append("http://m.fangxuele.com/t/#/profile'>【点击进入】</a>");
		WechatMessage.setDefaultReplyMsg(defaultContent.toString());

		// 手动关注消息
		StringBuilder subscribeContent = new StringBuilder();
		subscribeContent.append("hi~你来了[玫瑰] \n \n 每周三发福利，免费活动、商场特惠活动、亲子游，一周最好玩为你一网打尽！\n \n").append("本周热门：<a href='")
				.append("http://mp.weixin.qq.com/mp/homepage?__biz=MzI3NzA1Njc1Nw==&hid=4&sn=5fa0feee25624657ffe9391a8b442512#wechat_redirect'")
				.append("'>【点击进入】</a> \n \n 粉丝福利：微信公众号后台回复【玩博会】，即可参加免费领门票活动。");
		WechatMessage.setSubscribeMsg(subscribeContent.toString());

		// 地推二维码扫码消息
		StringBuilder dituiurl = new StringBuilder();
		dituiurl.append("hi~终于等到你[玫瑰] \n \n 你有一份福利未领取，<a href='").append(PropertiesUtil.getProperty(GIFTURL))
				.append("'>【猛戳领取】</a>");
		WechatMessage.setSubscribeQrcodeMsg(dituiurl.toString());

		// 扫码二次关注消息
		WechatMessage.setScanMsg("请点击下方菜单栏【我要免费玩】，积分兑换免费活动");

		// 客服回复的消息
		WechatMessage.setCustomerServiceMsg(
				"hi~亲你来了 [调皮]\n \n咨询热线：010-53689210（周一至周五10:00-19:00\n官方微信客服小安：18612183438或imxiaoan（全天在线） \n \n 订单查询：<a href='http://m.fangxuele.com/t/#/main-profile'>【点击进入】</a>");
		logger.warn("微信信息初始化完成！");
		
		// 生成海报回复消息
		StringBuilder posterMsg = new StringBuilder();
		posterMsg.append("您的零到壹专属海报正在生成中，请稍等片刻...\n\n");
		posterMsg.append("分享下方图片到朋友圈和微信群，邀请好友关注零到壹就能赚积分。戳我去").append("<a href='").append("http://m.fangxuele.com/t/#/profile-bonus'>【积分商城】</a>兑换好玩的活动。");
		WechatMessage.setMakePosterMsg(posterMsg.toString());
		
		// 被扫码人增加积分时分送的消息
		StringBuilder pesonBounsMsg = new StringBuilder();
		pesonBounsMsg.append("通过您的专属海报关注了“零到壹”，为您增加10积分。").append("<a href='").append("http://m.fangxuele.com/t/#/profile-bonus'>【戳我去积分商城】>></a>");
		WechatMessage.setPesonBounsMsg(pesonBounsMsg.toString());
		
		
//		// 初始化场景码
//		InitInfoService initInfoService = SpringContextHolder.getBean(InitInfoService.class);
//		initInfoService.initMaxPointCode();
//		logger.warn("场景码信息初始化完成！");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	public void destroy() {

		HttpClientUtil.destroy();
		logger.warn("Http客户端类卸载完成！");
		super.destroy();
	}
}