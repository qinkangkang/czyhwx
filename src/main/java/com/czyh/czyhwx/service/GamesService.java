package com.czyh.czyhwx.service;

import java.util.List;

import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.entity.TSceneUser;


public interface GamesService {

	public ResponseDTO getShareHelp(String customerBargainingId, String ticket, Integer clientType);

	List<TSceneUser> testMo();
	
	void testMoUpdate(String id,String openId);

}
