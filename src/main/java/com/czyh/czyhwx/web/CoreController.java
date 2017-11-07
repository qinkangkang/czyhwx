package com.czyh.czyhwx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springside.modules.mapper.JsonMapper;
import org.springframework.http.MediaType;

import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.CoreService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 微信主体类
 * 
 * @author jsz
 *
 */
@RestController
@RequestMapping(value = "/wechat/api/core", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CoreController extends GenericController {

	private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	protected WxMpConfigStorage configStorage;

	@Autowired
	protected WxMpService wxMpService;

	@Autowired
	protected CoreService coreService;

	// @RequestMapping(value = "index")
	// public String index() {
	// return "index";
	// }

	/**
	 * 微信公众号webservice主服务接口，提供与微信服务器的信息交互
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/wechatCore", method = { RequestMethod.GET, RequestMethod.POST })
	public void wechatCore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
			// 消息签名不正确，说明不是公众平台发过来的消息
			response.getWriter().println("非法请求");
			return;
		}

		String echoStr = request.getParameter("echostr");
		if (StringUtils.isNotBlank(echoStr)) {
			// 说明是一个仅仅用来验证的请求，回显echostr
			String echoStrOut = String.copyValueOf(echoStr.toCharArray());
			response.getWriter().println(echoStrOut);
			return;
		}

		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
				: request.getParameter("encrypt_type");

		if ("raw".equals(encryptType)) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
			response.getWriter().write(outMessage.toXml());
			return;
		}

		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), this.configStorage,
					timestamp, nonce, msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.coreService.route(inMessage);
			this.logger.info(response.toString());
			response.getWriter().write(outMessage.toEncryptedXml(this.configStorage));
			return;
		}

		response.getWriter().println("不可识别的加密类型");
		return;
	}

	/**
	 * 通过code获得基本用户信息(微信授权用) 详情请见:
	 * http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	 *
	 * @param response
	 * @param code
	 *            code
	 * @param lang
	 *            zh_CN, zh_TW, en
	 * @return
	 */
	@RequestMapping(value = "/getOAuth2UserInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String getOAuth2UserInfo(HttpServletResponse response, @RequestParam(value = "code") String code,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			responseDTO = this.coreService.getOAuth2UserInfo(code, "zh_CN");
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("微信授权登录时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

	/**
	 * getWechatJssdk
	 * 
	 * @param response
	 * @param code
	 * @param callback
	 * @return
	 */
	@RequestMapping(value = "/getWechatJssdk", method = { RequestMethod.GET, RequestMethod.POST })
	public String getWechatJssdk(HttpServletResponse response, @RequestParam(value = "url") String url,
			@RequestParam(value = "callback", required = false) String callback) {
		ResponseDTO responseDTO = null;
		try {
			responseDTO = coreService.getWechatJssdk(url);
		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("微信授权登录时出错！");
		}
		if (StringUtils.isBlank(callback)) {
			return mapper.toJson(responseDTO);
		} else {
			return mapper.toJsonP(callback, responseDTO);
		}

	}

}