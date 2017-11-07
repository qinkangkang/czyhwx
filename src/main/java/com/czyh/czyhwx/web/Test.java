package com.czyh.czyhwx.web;

import java.util.Map;

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
import com.czyh.czyhwx.service.SceneUserService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@RestController
@RequestMapping(value = "/wechat/api/Test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Test {

	private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private SceneUserService sceneUserService;

	@RequestMapping(value = "/Test", method = { RequestMethod.GET, RequestMethod.POST })
	public String createMenu(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback,
			@RequestParam(value = "openid", required = false) String openid) {
		ResponseDTO responseDTO = null;
		try {
			responseDTO = sceneUserService.updateSceneUserAgain(openid);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("测试出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	@RequestMapping(value = "/Test2", method = { RequestMethod.GET, RequestMethod.POST })
	public String Test2(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback,
			@RequestParam(value = "openid", required = false) String openid) {
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> mp = sceneUserService.getSceneUser(openid);
			System.out.println(mp.get("type").toString());
			System.out.println(mp.get("time").toString());

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("测试出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	@RequestMapping(value = "/test3", method = { RequestMethod.GET, RequestMethod.POST })
	public String test3(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		try {
			sceneUserService.test2();

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("测试出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}
}
