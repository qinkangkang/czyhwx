package com.czyh.czyhwx.web;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.czyh.czyhwx.entity.TSceneUser;
import com.czyh.czyhwx.service.GamesService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;

@RestController
@RequestMapping(value = "/wechat/api/games", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GameController {

	private static Logger logger = LoggerFactory.getLogger(GameController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private GamesService gamesService;

	@RequestMapping(value = "/getShareHelp", method = { RequestMethod.GET, RequestMethod.POST })
	public String getShareHelp(HttpServletResponse response,
			@RequestParam(value = "customerBargainingId") String customerBargainingId,
			@RequestParam(value = "ticket") String ticket,
			@RequestParam(value = "clientType", required = false, defaultValue = "1") Integer clientType,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;

		try {
			responseDTO = gamesService.getShareHelp(customerBargainingId, ticket, clientType);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("获取用户渠道二维码时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	@RequestMapping(value = "/testMo", method = { RequestMethod.GET, RequestMethod.POST })
	public String testMo(HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		try {
			List<TSceneUser> tSceneUserList = gamesService.testMo();

			for (TSceneUser tSceneUser : tSceneUserList) {

				gamesService.testMoUpdate(tSceneUser.getId(), tSceneUser.getFopenId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("更新数据库数据！");
		}

		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	@RequestMapping(value = "/uploadLogo", method = { RequestMethod.GET, RequestMethod.POST })
	public String uploadLogo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "callback", required = false) String callback) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResponseDTO responseDTO = null;
		try {
			// CommonsMultipartFile cf = (CommonsMultipartFile) file;
			// DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			// File f = fi.getStoreLocation();

			File file1 = new File("D://back.jpg");
			WxMpMaterial wxMaterial = new WxMpMaterial();
			wxMaterial.setFile(file1);
			wxMaterial.setName("test");
			WxMpMaterialUploadResult res;

			res = wxMpService.getMaterialService().materialFileUpload("image", wxMaterial);

			System.out.println(res.getMediaId() + "|" + res.getUrl());

		} catch (WxErrorException e) {

			e.printStackTrace();
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}
	}

}