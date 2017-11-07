package com.czyh.czyhwx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czyh.czyhwx.config.MainConfig;
import com.czyh.czyhwx.dao.WxMenuDAO;
import com.czyh.czyhwx.dao.WxMenuItemDAO;
import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.entity.TWxMenu;
import com.czyh.czyhwx.entity.TWxMenuItem;
import com.czyh.czyhwx.service.MenuService;
import com.google.common.collect.Maps;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

@Service
@Transactional
public class MenuSeviceImpl implements MenuService {

	@Autowired
	private WxMenuDAO wxMenuDAO;

	@Autowired
	private WxMenuItemDAO wxMenuItemDAO;

	// TODO 自定义菜单待完善等待判断逻辑
	@Override
	public ResponseDTO createMenu() {

		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();

		ResponseDTO responseDTO = new ResponseDTO();
		Map<String, Object> returnData = Maps.newHashMap();
		List<TWxMenu> list = wxMenuDAO.getMenuList();

		// 第一栏下子菜单
		List<TWxMenuItem> tWxMenuItemList1 = wxMenuItemDAO.getMenuList(list.get(0).getId());
		// for (TWxMenuItem tWxMenuItem : tWxMenuItemList1) {
		// System.out.println(tWxMenuItem.getItemName());
		// }
		// 第二栏下子菜单
		List<TWxMenuItem> tWxMenuItemList2 = wxMenuItemDAO.getMenuList(list.get(1).getId());

		// for (TWxMenuItem tWxMenuItem : tWxMenuItemList2) {
		// System.out.println(tWxMenuItem.getItemName());
		// }
		// 第三栏下子菜单
		List<TWxMenuItem> tWxMenuItemList3 = wxMenuItemDAO.getMenuList(list.get(2).getId());
		// for (TWxMenuItem tWxMenuItem : tWxMenuItemList3) {
		// System.out.println(tWxMenuItem.getItemName());
		// }

		WxMenu menu = new WxMenu();

		// 第一栏主菜单
		WxMenuButton button1 = new WxMenuButton();
		if (list.get(0).getType() == 1) {
			button1.setType(WxConsts.BUTTON_CLICK);
			button1.setName(list.get(0).getMenuName());
			button1.setUrl(list.get(0).getContent());
		} else if (list.get(0).getType() == 2) {
			button1.setType(WxConsts.BUTTON_VIEW);
			button1.setName(list.get(0).getMenuName());
			button1.setUrl(list.get(0).getUrl());
		} else if (list.get(0).getType() == 0) {
			button1.setName(list.get(0).getMenuName());
		}

		// 第一栏子菜单上边数第一个开始
		// 上边第五个菜单
		WxMenuButton button15 = new WxMenuButton();
		if (tWxMenuItemList1.get(4).getType() == 1) {
			button15.setType(WxConsts.BUTTON_CLICK);
			button15.setName(tWxMenuItemList1.get(4).getItemName());
			button15.setUrl(tWxMenuItemList1.get(4).getText());
		} else if (tWxMenuItemList1.get(4).getType() == 2) {
			button15.setType(WxConsts.BUTTON_VIEW);
			button15.setName(tWxMenuItemList1.get(4).getItemName());
			button15.setUrl(tWxMenuItemList1.get(4).getContent());
		}

		// 上边第四个菜单
		WxMenuButton button14 = new WxMenuButton();
		if (tWxMenuItemList1.get(3).getType() == 1) {
			button14.setType(WxConsts.BUTTON_CLICK);
			button14.setName(tWxMenuItemList1.get(3).getItemName());
			button14.setUrl(tWxMenuItemList1.get(3).getText());
		} else if (tWxMenuItemList1.get(3).getType() == 2) {
			button14.setType(WxConsts.BUTTON_VIEW);
			button14.setName(tWxMenuItemList1.get(3).getItemName());
			button14.setUrl(tWxMenuItemList1.get(3).getContent());
		}

		// 上边第三个菜单
		WxMenuButton button13 = new WxMenuButton();
		if (tWxMenuItemList1.get(2).getType() == 1) {
			button13.setType(WxConsts.BUTTON_CLICK);
			button13.setName(tWxMenuItemList1.get(2).getItemName());
			button13.setUrl(tWxMenuItemList1.get(2).getText());
		} else if (tWxMenuItemList1.get(2).getType() == 2) {
			button13.setType(WxConsts.BUTTON_VIEW);
			button13.setName(tWxMenuItemList1.get(2).getItemName());
			button13.setUrl(tWxMenuItemList1.get(2).getContent());
		}

		// 上边第二个菜单
		WxMenuButton button12 = new WxMenuButton();
		if (tWxMenuItemList1.get(1).getType() == 1) {
			button12.setType(WxConsts.BUTTON_CLICK);
			button12.setName(tWxMenuItemList1.get(1).getItemName());
			button12.setUrl(tWxMenuItemList1.get(1).getText());
		} else if (tWxMenuItemList1.get(1).getType() == 2) {
			button12.setType(WxConsts.BUTTON_VIEW);
			button12.setName(tWxMenuItemList1.get(1).getItemName());
			button12.setUrl(tWxMenuItemList1.get(1).getContent());
		}

		// 上边第一个菜单
		WxMenuButton button11 = new WxMenuButton();
		if (tWxMenuItemList1.get(0).getType() == 1) {
			button11.setType(WxConsts.BUTTON_CLICK);
			button11.setName(tWxMenuItemList1.get(0).getItemName());
			button11.setKey(tWxMenuItemList1.get(0).getText());
		} else if (tWxMenuItemList1.get(0).getType() == 2) {
			button11.setType(WxConsts.BUTTON_VIEW);
			button11.setName(tWxMenuItemList1.get(0).getItemName());
			button11.setUrl(tWxMenuItemList1.get(0).getContent());
		}

		if (tWxMenuItemList1.get(0).getMenuOrder() == 1) {
			button1.getSubButtons().add(button11);
		}

		if (tWxMenuItemList1.get(1).getMenuOrder() == 1) {
			button1.getSubButtons().add(button12);
		}

		if (tWxMenuItemList1.get(2).getMenuOrder() == 1) {
			button1.getSubButtons().add(button13);
		}

		if (tWxMenuItemList1.get(3).getMenuOrder() == 1) {
			button1.getSubButtons().add(button14);
		}

		if (tWxMenuItemList1.get(4).getMenuOrder() == 1) {
			button1.getSubButtons().add(button15);
		}

		// 第二栏主菜单
		WxMenuButton button2 = new WxMenuButton();
		if (list.get(1).getType() == 1) {
			button2.setType(WxConsts.BUTTON_CLICK);
			button2.setName(list.get(1).getMenuName());
			button2.setUrl(list.get(1).getContent());
		} else if (list.get(1).getType() == 2) {
			button2.setType(WxConsts.BUTTON_VIEW);
			button2.setName(list.get(1).getMenuName());
			button2.setUrl(list.get(1).getUrl());
		} else if (list.get(1).getType() == 0) {
			button2.setName(list.get(1).getMenuName());
		}

		// 第二栏子菜单上边数第一个

		// 上边第五个菜单
		WxMenuButton button25 = new WxMenuButton();
		if (tWxMenuItemList2.get(4).getType() == 1) {
			button25.setType(WxConsts.BUTTON_CLICK);
			button25.setName(tWxMenuItemList2.get(4).getItemName());
			button25.setKey(tWxMenuItemList2.get(4).getText());
		} else if (tWxMenuItemList2.get(4).getType() == 2) {
			button25.setType(WxConsts.BUTTON_VIEW);
			button25.setName(tWxMenuItemList2.get(4).getItemName());
			button25.setUrl(tWxMenuItemList2.get(4).getContent());
		}

		// 上边第四个菜单
		WxMenuButton button24 = new WxMenuButton();
		if (tWxMenuItemList2.get(3).getType() == 1) {
			button24.setType(WxConsts.BUTTON_CLICK);
			button24.setName(tWxMenuItemList2.get(3).getItemName());
			button24.setKey(tWxMenuItemList2.get(3).getText());
		} else if (tWxMenuItemList2.get(3).getType() == 2) {
			button24.setType(WxConsts.BUTTON_VIEW);
			button24.setName(tWxMenuItemList2.get(3).getItemName());
			button24.setUrl(tWxMenuItemList2.get(3).getContent());
		}

		// 上边第三个菜单
		WxMenuButton button23 = new WxMenuButton();
		if (tWxMenuItemList2.get(2).getType() == 1) {
			button23.setType(WxConsts.BUTTON_CLICK);
			button23.setName(tWxMenuItemList2.get(2).getItemName());
			button23.setKey(tWxMenuItemList2.get(2).getText());
		} else if (tWxMenuItemList2.get(2).getType() == 2) {
			button23.setType(WxConsts.BUTTON_VIEW);
			button23.setName(tWxMenuItemList2.get(2).getItemName());
			button23.setUrl(tWxMenuItemList2.get(2).getContent());
		}

		// 上边第二个菜单
		WxMenuButton button22 = new WxMenuButton();
		if (tWxMenuItemList2.get(1).getType() == 1) {
			button22.setType(WxConsts.BUTTON_CLICK);
			button22.setName(tWxMenuItemList2.get(1).getItemName());
			button22.setKey(tWxMenuItemList2.get(1).getText());
		} else if (tWxMenuItemList2.get(1).getType() == 2) {
			button22.setType(WxConsts.BUTTON_VIEW);
			button22.setName(tWxMenuItemList2.get(1).getItemName());
			button22.setUrl(tWxMenuItemList2.get(1).getContent());
		}

		// 上边第一个菜单
		WxMenuButton button21 = new WxMenuButton();
		if (tWxMenuItemList2.get(0).getType() == 1) {
			button21.setType(WxConsts.BUTTON_CLICK);
			button21.setName(tWxMenuItemList2.get(0).getItemName());
			button21.setKey(tWxMenuItemList2.get(0).getText());
		} else if (tWxMenuItemList2.get(0).getType() == 2) {
			button21.setType(WxConsts.BUTTON_VIEW);
			button21.setName(tWxMenuItemList2.get(0).getItemName());
			button21.setUrl(tWxMenuItemList2.get(0).getContent());
		}

		if (tWxMenuItemList2.get(0).getMenuOrder() == 1) {
			button2.getSubButtons().add(button21);
		}

		if (tWxMenuItemList2.get(1).getMenuOrder() == 1) {
			button2.getSubButtons().add(button22);
		}

		if (tWxMenuItemList2.get(2).getMenuOrder() == 1) {
			button2.getSubButtons().add(button23);
		}

		if (tWxMenuItemList2.get(3).getMenuOrder() == 1) {
			button2.getSubButtons().add(button24);
		}

		if (tWxMenuItemList2.get(4).getMenuOrder() == 1) {
			button2.getSubButtons().add(button25);
		}

		// 第三栏主菜单
		WxMenuButton button3 = new WxMenuButton();
		if (list.get(2).getType() == 1) {
			button2.setType(WxConsts.BUTTON_CLICK);
			button2.setName(list.get(2).getMenuName());
			button2.setUrl(list.get(2).getContent());
		} else if (list.get(2).getType() == 2) {
			button2.setType(WxConsts.BUTTON_VIEW);
			button2.setName(list.get(2).getMenuName());
			button2.setUrl(list.get(2).getUrl());
		} else if (list.get(2).getType() == 0) {
			button3.setName(list.get(2).getMenuName());
		}

		// 第三栏子菜单

		// 上边第二个菜单
		WxMenuButton button35 = new WxMenuButton();
		if (tWxMenuItemList3.get(4).getType() == 1) {
			button35.setType(WxConsts.BUTTON_CLICK);
			button35.setName(tWxMenuItemList3.get(4).getItemName());
			button35.setKey(tWxMenuItemList3.get(4).getText());
		} else if (tWxMenuItemList3.get(4).getType() == 2) {
			button35.setType(WxConsts.BUTTON_VIEW);
			button35.setName(tWxMenuItemList3.get(4).getItemName());
			button35.setUrl(tWxMenuItemList3.get(4).getContent());
		}

		// 上边第二个菜单
		WxMenuButton button34 = new WxMenuButton();
		if (tWxMenuItemList3.get(3).getType() == 1) {
			button34.setType(WxConsts.BUTTON_CLICK);
			button34.setName(tWxMenuItemList3.get(3).getItemName());
			button34.setKey(tWxMenuItemList3.get(3).getText());
		} else if (tWxMenuItemList3.get(3).getType() == 2) {
			button34.setType(WxConsts.BUTTON_VIEW);
			button34.setName(tWxMenuItemList3.get(3).getItemName());
			button34.setUrl(tWxMenuItemList3.get(3).getContent());
		}

		// 上边第二个菜单
		WxMenuButton button33 = new WxMenuButton();
		if (tWxMenuItemList3.get(2).getType() == 1) {
			button33.setType(WxConsts.BUTTON_CLICK);
			button33.setName(tWxMenuItemList3.get(2).getItemName());
			button33.setKey(tWxMenuItemList3.get(2).getText());
		} else if (tWxMenuItemList3.get(2).getType() == 2) {
			button33.setType(WxConsts.BUTTON_VIEW);
			button33.setName(tWxMenuItemList3.get(2).getItemName());
			button33.setUrl(tWxMenuItemList3.get(2).getContent());
		}

		// 上边第二个菜单
		WxMenuButton button32 = new WxMenuButton();
		if (tWxMenuItemList3.get(1).getType() == 1) {
			button32.setType(WxConsts.BUTTON_CLICK);
			button32.setName(tWxMenuItemList3.get(1).getItemName());
			button32.setKey(tWxMenuItemList3.get(1).getText());
		} else if (tWxMenuItemList3.get(1).getType() == 2) {
			button32.setType(WxConsts.BUTTON_VIEW);
			button32.setName(tWxMenuItemList3.get(1).getItemName());
			button32.setUrl(tWxMenuItemList3.get(1).getContent());
		}

		// 上边第一个菜单
		WxMenuButton button31 = new WxMenuButton();
		if (tWxMenuItemList3.get(0).getType() == 1) {
			button31.setType(WxConsts.BUTTON_CLICK);
			button31.setName(tWxMenuItemList3.get(0).getItemName());
			button31.setKey(tWxMenuItemList3.get(0).getText());
		} else if (tWxMenuItemList3.get(0).getType() == 2) {
			button31.setType(WxConsts.BUTTON_VIEW);
			button31.setName(tWxMenuItemList3.get(0).getItemName());
			button31.setUrl(tWxMenuItemList3.get(0).getContent());
		}

		if (tWxMenuItemList3.get(0).getMenuOrder() == 1) {
			button3.getSubButtons().add(button31);
		}

		if (tWxMenuItemList3.get(1).getMenuOrder() == 1) {
			button3.getSubButtons().add(button32);
		}

		if (tWxMenuItemList3.get(2).getMenuOrder() == 1) {
			button3.getSubButtons().add(button33);
		}

		if (tWxMenuItemList3.get(3).getMenuOrder() == 1) {
			button3.getSubButtons().add(button34);
		}

		if (tWxMenuItemList3.get(4).getMenuOrder() == 1) {
			button3.getSubButtons().add(button35);
		}

		// 创建菜单
		menu.getButtons().add(button1);
		menu.getButtons().add(button2);
		menu.getButtons().add(button3);

		try {
			wxMpService.getMenuService().menuCreate(menu);
			WxMpMenu wxMenu = wxMpService.getMenuService().menuGet();
			returnData.put("wxMenuList", wxMenu);
			System.out.println(menu);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		responseDTO.setSuccess(true);
		responseDTO.setStatusCode(0);
		responseDTO.setMsg("创建自定义菜单成功");
		responseDTO.setData(returnData);
		return responseDTO;
	}

	public void deleteMenu() {
		MainConfig mainConfig = new MainConfig();
		WxMpService wxMpService = mainConfig.wxMpService();
		// 清空菜单
		try {
			wxMpService.getMenuService().menuDelete();
		} catch (WxErrorException e1) {
			e1.printStackTrace();
		}
	}
}
