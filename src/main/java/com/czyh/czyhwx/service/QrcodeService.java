package com.czyh.czyhwx.service;

import com.czyh.czyhwx.dto.m.ResponseDTO;

public interface QrcodeService {

	public ResponseDTO getQrCodeCreateTmpTicket(Integer scene, Integer expireSeconds);
	
	public ResponseDTO getQrCodeCreateLastTicket(String scene);

}
