package com.czyh.czyhwx.web;

import javax.servlet.http.HttpServletResponse;

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
import com.czyh.czyhwx.service.TemplateMessageService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 微信模版消息
 * 
 * @author jinshengzhi
 *
 */
@RestController
@RequestMapping(value = "/wechat/api/templateMessage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TemplateMessageController extends GenericController {

	private static final Logger logger = LoggerFactory.getLogger(CoreController.class);

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	@Autowired
	private WxMpConfigStorage configStorage;

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private TemplateMessageService templateMessageService;

	/**
	 * 订单支付成功模版消息通知
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param orderMoneySum
	 * @param orderProductName
	 * @param orderName
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyOrderPaySuccessTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyOrderPaySuccessTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "orderMoneySum") String orderMoneySum,
			@RequestParam(value = "orderProductName") String orderProductName,
			@RequestParam(value = "orderName") String orderName, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyOrderPaySuccessTemplate(openid, first, orderMoneySum,
					orderProductName, orderName, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送订单支付成功消息通知失败！");
		}

		return mapper.toJson(responseDTO);
	}

	/**
	 * 退款申请通知模板消息
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyRefundTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyRefundTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "refundMoneySum") String refundMoneySum,
			@RequestParam(value = "refundProductName") String refundProductName,
			@RequestParam(value = "refundName") String refundName, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyRefundTemplate(openid, first, refundMoneySum, refundProductName,
					refundName, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送退款申请通知消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 订单未支付提醒通知模板消息
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyOrderUnPayTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyOrderUnPayTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "ordertape") String ordertape,
			@RequestParam(value = "ordeID") String ordeID, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyOrderUnPayTemplate(openid, first, ordertape, ordeID, remark,
					url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送订单未支付提醒消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 订单评价提醒模板消息
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyOrderEvaluationTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyOrderEvaluationTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyOrderEvaluationTemplate(openid, first, keyword1, keyword2,
					remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送订单评价提醒消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 咨询回复消息提醒模板消息
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyReplyConsultTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyReplyConsultTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyReplyConsultTemplate(openid, first, keyword1, keyword2, remark,
					url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送咨询回复消息提消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 评论回复消息提醒模板消息
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyCommentConsultTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyCommentConsultTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "keyword3") String keyword3, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyCommentConsultTemplate(openid, first, keyword1, keyword2,
					keyword3, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送评论回复消息提消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 到账提醒模板消息(慎用)
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyArrivalTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyArrivalTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "keyword1") String keyword1,
			@RequestParam(value = "keyword2") String keyword2, @RequestParam(value = "keyword3") String keyword3,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyArrivalTemplate(openid, first, keyword1, keyword2, keyword3,
					remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送到账提醒消息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 积分到账提醒单发
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyBounsTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyBounsTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "keyword1") String keyword1,
			@RequestParam(value = "keyword2") String keyword2, @RequestParam(value = "keyword3") String keyword3,
			@RequestParam(value = "keyword4") String keyword4, @RequestParam(value = "keyword5") String keyword5,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyBounsTemplate(openid, first, keyword1, keyword2, keyword3,
					keyword4, keyword5, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发评价订单提醒息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 积分到账提醒List
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyBounsListTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyBounsListTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyBounsListTemplate();

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发评价订单提醒息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 评价订单提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyOrderCommentTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyOrderCommentTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyOrderCommentTemplate(openid, first, keyword1, keyword2, remark,
					url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发评价订单提醒息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 客服回复提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyCustomerServiceTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyCustomerServiceTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "keyword3") String keyword3, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyCustomerServiceTemplate(openid, first, keyword1, keyword2,
					keyword3, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发评价订单提醒息失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 奖品兑换消息提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyPrizeExchangeTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyPrizeExchangeTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "keyword3") String keyword3, @RequestParam(value = "keyword4") String keyword4,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyPrizeExchangeTemplate(openid, first, keyword1, keyword2,
					keyword3, keyword4, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发奖品兑换消息提醒失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 砍一砍活动快结束提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyBargainingEndTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyBargainingEndTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "keyword3") String keyword3, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyBargainingEndTemplate(openid, first, keyword1, keyword2,
					keyword3, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送砍一砍活动快结束失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 砍一砍库存不足提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyInventoryShortageTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyInventoryShortageTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "keyword3") String keyword3, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyInventoryShortageTemplate(openid, first, keyword1, keyword2,
					keyword3, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送砍一砍库存不足失败！");
		}
		return mapper.toJson(responseDTO);
	}

	/**
	 * 砍一砍开始提醒
	 * 
	 * @param response
	 * @param openid
	 * @param first
	 * @param OrderSn
	 * @param OrderStatus
	 * @param remark
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/notifyJoinTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyJoinTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "keyword1") String keyword1,
			@RequestParam(value = "keyword2") String keyword2, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyJoinTemplate(openid, first, keyword1, keyword2, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送砍一砍开始失败！");
		}
		return mapper.toJson(responseDTO);
	}

	@RequestMapping(value = "/notifyPrizeTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyPrizeTemplate(HttpServletResponse response, @RequestParam(value = "openid") String openid,
			@RequestParam(value = "first") String first, @RequestParam(value = "keyword1") String keyword1,
			@RequestParam(value = "keyword2") String keyword2, @RequestParam(value = "keyword3") String keyword3,
			@RequestParam(value = "keyword4") String keyword4, @RequestParam(value = "remark") String remark,
			@RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyPrizeTemplate(openid, first, keyword1, keyword2, keyword3,
					keyword4, remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发兑奖成功失败！");
		}
		return mapper.toJson(responseDTO);
	}

	@RequestMapping(value = "/notifyComplimentaryPointsTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyComplimentaryPointsTemplate(HttpServletResponse response,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "first") String first,
			@RequestParam(value = "keyword1") String keyword1, @RequestParam(value = "keyword2") String keyword2,
			@RequestParam(value = "remark") String remark, @RequestParam(value = "url") String url) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyComplimentaryPointsTemplate(openid, first, keyword1, keyword2,
					remark, url);

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送评论返现提醒失败！");
		}
		return mapper.toJson(responseDTO);
	}

	@RequestMapping(value = "/notifyNewEmailTemplate", method = { RequestMethod.GET, RequestMethod.POST })
	public String notifyNewEmailTemplate(HttpServletResponse response) {
		ResponseDTO responseDTO = null;

		try {

			responseDTO = templateMessageService.notifyNewEmailTemplate();

		} catch (Exception e) {
			e.printStackTrace();
			responseDTO = new ResponseDTO();
			responseDTO.setSuccess(false);
			responseDTO.setStatusCode(200);
			responseDTO.setMsg("发送评论返现提醒失败！");
		}
		return mapper.toJson(responseDTO);
	}

}
