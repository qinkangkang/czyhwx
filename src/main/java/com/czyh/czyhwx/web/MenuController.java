package com.czyh.czyhwx.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.JsonMapper;

import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.MenuService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@RestController
@RequestMapping(value = "/wechat/api/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController {

	private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/createMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String createMenu(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		try {
			responseDTO = menuService.createMenu();
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("创建自定义菜单时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	@RequestMapping(value = "/deleteMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteMenu(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		try {
			menuService.deleteMenu();
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("删除自定义菜单时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}
	}
}
