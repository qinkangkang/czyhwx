package com.czyh.czyhwx.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.czyh.czyhwx.dto.m.ResponseDTO;

public interface SceneUserService {

	public ResponseDTO updateSceneUserAgain(String openid);

	public ResponseDTO saveSceneInfo(String sceneCode, String openid, Integer type, Date sSTime);

	public Map<String, Object> getSceneUser(String openid);

	public String getSenceUserTime(String openid);

	public ResponseDTO addBounsQrSubscribe(Integer clientType, String unionid, String openid, String nickname,
			String logoUrl, String country, String province, String city, Integer sex, String gps, Long subscribeTime,
			Integer sceneId, String sceneStr);
	
	public ResponseDTO addSceneUser(Integer clientType, String unionid, String openid, String nickname,
			String logoUrl, String country, String province, String city, Integer sex, String gps, Long subscribeTime,
			Integer sceneId, String sceneStr);

	public String getSceneUserExist(String openid);

	public String getChannelType(String openid);

	public ResponseDTO updateSceneUser(String openid, Long subscribeTime);
	
	public Map<String, String> getUserInfoWxPoster(String openid);
	
	public String getMAXPointCode();
	
	public ResponseDTO updateUserInfoWxPoster(String openid, String fpointCode, Date finvalidTime);
	
	public ResponseDTO setSceneGPS(String openid, String gps);
	
	public String getQrcode(String openid);
	
	public ResponseDTO updateSceneStr(String openid, String str);
	
	public String findByCustomerInfo(String openid);
	
	public ResponseDTO updateSceneUserUnsub(String openid);
	
	public void updateCityName(String openid, String gps);
	
	public void test2();
}
