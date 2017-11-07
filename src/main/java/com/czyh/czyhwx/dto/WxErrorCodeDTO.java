package com.czyh.czyhwx.dto;

import java.io.Serializable;

import com.czyh.czyhwx.util.NullToEmptySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WxErrorCodeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String errorCode;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String errorDesc;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String errorReason;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String errorSolution;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public String getErrorSolution() {
		return errorSolution;
	}

	public void setErrorSolution(String errorSolution) {
		this.errorSolution = errorSolution;
	}

}