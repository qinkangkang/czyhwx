package com.czyh.czyhwx.dto;

import java.io.Serializable;

import com.czyh.czyhwx.util.NullToEmptySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WxPayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String partnerTradeNo;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String paymentNo;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String paymentTime;

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

}