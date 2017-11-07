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
import com.czyh.czyhwx.service.QrcodeService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import me.chanjar.weixin.mp.api.WxMpService;

@RestController
@RequestMapping(value = "/wechat/api/qrcode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QrcodeController {

	private static Logger logger = LoggerFactory.getLogger(QrcodeController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private QrcodeService qrcodeService;

	/**
	 * 生成临时二维码
	 * 
	 * @param response
	 * @param sceneId
	 * @param expireSeconds
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/getQrCodeCreateTmpTicket", method = { RequestMethod.GET, RequestMethod.POST })
	public String getQrCodeCreateTmpTicket(HttpServletResponse response, @RequestParam(value = "scene") Integer scene,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = qrcodeService.getQrCodeCreateTmpTicket(scene, 2592000);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("生产临时二维码时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	/**
	 * 生成永久二维码
	 * 
	 * @param response
	 * @param sceneId
	 * @param expireSeconds
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/getQrCodeCreateLastTicket", method = { RequestMethod.GET, RequestMethod.POST })
	public String getQrCodeCreateLastTicket(HttpServletResponse response, @RequestParam(value = "scene") String scene,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = qrcodeService.getQrCodeCreateLastTicket(scene);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("生产永久二维码时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

}
