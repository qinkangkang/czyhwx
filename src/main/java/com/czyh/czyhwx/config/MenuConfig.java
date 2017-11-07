package com.czyh.czyhwx.config;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信自定义菜单配置
 * 
 * @author jinshengzhi
 *
 */
public class MenuConfig {

	/**
	 * 定义菜单结构
	 *
	 * @return
	 */
	public static WxMenu getMenu() {

		// MainConfig mainConfig = new MainConfig();
		// WxMpService wxMpService = mainConfig.wxMpService();

		WxMenu menu = new WxMenu();
		// 第一栏子菜单
		WxMenuButton button1 = new WxMenuButton();
		button1.setType(WxConsts.BUTTON_VIEW);
		button1.setName("优惠商城");
		button1.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/home");

//		WxMenuButton button11 = new WxMenuButton();
//		button11.setType(WxConsts.BUTTON_VIEW);
//		button11.setName("下载用户APP");
//		button11.setUrl("http://czyhsd.021-sdeals.cn/udownload/");
//
//		WxMenuButton button12 = new WxMenuButton();
//		button12.setType(WxConsts.BUTTON_VIEW);
//		button12.setName("下载商户APP");
//		button12.setUrl("http://czyhsd.021-sdeals.cn/mdownload/");

		// WxMenuButton button13 = new WxMenuButton();
		// button13.setType(WxConsts.BUTTON_VIEW);
		// button13.setName("华联寻宝");
		// button13.setUrl("http://m.fangxuele.com/halloween/#/?carnivalId=bd216f7c-5b09-4efa-b4df-adbfb3032131");
		//
		// WxMenuButton button14 = new WxMenuButton();
		// button14.setType(WxConsts.BUTTON_VIEW);
		// button14.setName("幼教展寻宝");
		// button14.setUrl("http://m.fangxuele.com/halloween/#/?carnivalId=274a2cdf-5102-4e2e-88a8-4d3d327699c6");
		//
//		button1.getSubButtons().add(button11);
//		button1.getSubButtons().add(button12);
		// button1.getSubButtons().add(button13);
		// button1.getSubButtons().add(button14);

		// 第二栏菜单
		WxMenuButton button2 = new WxMenuButton();
//		button2.setType(WxConsts.BUTTON_VIEW);
		button2.setName("U惠福利");
//		button2.setUrl("http://lingdaoyi.tunnel.2bdata.com/h5test/sdeals/#/home");测试链接

		WxMenuButton button21 = new WxMenuButton();
		button21.setType(WxConsts.BUTTON_VIEW);
		button21.setName("新人礼包");
		button21.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/invite");
		
		WxMenuButton button22 = new WxMenuButton();
		button22.setType(WxConsts.BUTTON_VIEW);
		button22.setName("邀请有礼");
		button22.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/profile-invite");
		
		WxMenuButton button23 = new WxMenuButton();
		button23.setType(WxConsts.BUTTON_VIEW);
		button23.setName("领券中心");
		button23.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/coupon");
		
		WxMenuButton button24 = new WxMenuButton();
		button24.setType(WxConsts.BUTTON_VIEW);
		button24.setName("U币社");
		button24.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/profile-bonus");
		
		WxMenuButton button25 = new WxMenuButton();
		button25.setType(WxConsts.BUTTON_VIEW);
		button25.setName("一起砍价");
		button25.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/event-bargain");
		
		button2.getSubButtons().add(button21);
		button2.getSubButtons().add(button22);
		button2.getSubButtons().add(button23);
		button2.getSubButtons().add(button24);
		button2.getSubButtons().add(button25);
		
		
		// 第三栏菜单
		WxMenuButton button3 = new WxMenuButton();
		// 底部主菜单
//		button3.setType(WxConsts.BUTTON_VIEW);
		button3.setName("我的服务");
//		button3.setUrl("http://www.021-sdeals.com/");
		// button3.setKey("hbzz");

		// 第三栏菜单上边数第一个
		 WxMenuButton button31 = new WxMenuButton();
		 button31.setType(WxConsts.BUTTON_VIEW);
		 button31.setName("联系客服");
		 button31.setUrl("http://czyhimg.021-sdeals.cn/czyh/html/article/2017-06-17/59feb0ea-7844-4f12-98a1-6d8ac5fa4eef.html");

		 WxMenuButton button32 = new WxMenuButton();
		 button32.setType(WxConsts.BUTTON_VIEW);
		 button32.setName("个人中心");
		 button32.setUrl("http://czyhsd.021-sdeals.cn/sdeals/#/main-profile");

		 WxMenuButton button33 = new WxMenuButton();
		 button33.setType(WxConsts.BUTTON_VIEW);
		 button33.setName("优惠APP");
		 button33.setUrl("http://czyhsd.021-sdeals.cn/udownload/");
		 
//		 WxMenuButton button33 = new WxMenuButton();
//		 button33.setType(WxConsts.BUTTON_CLICK);
//		 button33.setName("优惠APP");
//		 button33.setKey("hbzz");

//		 WxMenuButton button34 = new WxMenuButton();
//		 button34.setType(WxConsts.BUTTON_VIEW);
//		 button34.setName("砍至五折");
//		 button34.setUrl(
//		 "http://czyhimg.021-sdeals.cn/czyh/html/article/2017-06-17/59feb0ea-7844-4f12-98a1-6d8ac5fa4eef.html");

		// WxMenuButton button35 = new WxMenuButton();
		// button35.setType(WxConsts.BUTTON_CLICK);
		// button35.setName("联系客服");
		// button35.setKey("http://czyhimg.021-sdeals.cn/czyh/html/article/2017-06-17/59feb0ea-7844-4f12-98a1-6d8ac5fa4eef.html");

		 button3.getSubButtons().add(button31);
		 button3.getSubButtons().add(button32);
		 button3.getSubButtons().add(button33);
//		 button3.getSubButtons().add(button34);
		// button3.getSubButtons().add(button35);

		menu.getButtons().add(button1);
		menu.getButtons().add(button2);
		menu.getButtons().add(button3);

		return menu;
	}

	/**
	 * 运行此main函数即可生成公众号自定义菜单
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();
		try {
			wxMpService.getMenuService().menuCreate(getMenu());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

}
