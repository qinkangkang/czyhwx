package com.czyh.czyhwx.service;

import com.czyh.czyhwx.dto.m.ResponseDTO;

public interface TemplateMessageService {

	public ResponseDTO notifyOrderPaySuccessTemplate(String openid, String first, String orderMoneySum,
			String orderProductName, String orderName, String remark, String url);

	public ResponseDTO notifyRefundTemplate(String openid, String first, String refundMoneySum,
			String refundProductName, String refundName, String remark, String url);

	public ResponseDTO notifyOrderUnPayTemplate(String openid, String first, String ordertape, String ordeID,
			String remark, String url);
	
	public ResponseDTO notifyOrderEvaluationTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url);
	
	public ResponseDTO notifyReplyConsultTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url);
	
	public ResponseDTO notifyCommentConsultTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url);
	
	public ResponseDTO notifyArrivalTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url);
	
	public ResponseDTO notifyBounsTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String keyword5, String remark, String url);
	
	public ResponseDTO notifyBounsListTemplate();

	public ResponseDTO notifyOrderCommentTemplate(String openid, String first, String keyword1, String keyword2,
			String remark, String url);
	
	public ResponseDTO notifyCustomerServiceTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url);
	
	public ResponseDTO notifyPrizeExchangeTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String remark, String url);
	
	public ResponseDTO notifyBargainingEndTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url);
	
	public ResponseDTO notifyInventoryShortageTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String remark, String url);
	
	public ResponseDTO notifyJoinTemplate(String openid, String first, String keyword1, String keyword2, String remark,
			String url);
	
	public ResponseDTO notifyPrizeTemplate(String openid, String first, String keyword1, String keyword2,
			String keyword3, String keyword4, String remark, String url);
	
	public ResponseDTO notifyComplimentaryPointsTemplate(String openid, String first, String keyword1, String keyword2, String remark,
			String url);
	
	public ResponseDTO notifyNewEmailTemplate();
			
}
