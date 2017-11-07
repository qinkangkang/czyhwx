package com.czyh.czyhwx.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springside.modules.mapper.JsonMapper;

import com.czyh.czyhwx.dto.m.ResponseDTO;
import com.czyh.czyhwx.service.InitInfoService;
import com.czyh.czyhwx.util.HttpClientUtil;
import com.czyh.czyhwx.util.PropertiesUtil;
import com.czyh.czyhwx.util.WechatMessage;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;

@Service
public class InitInfoServiceImpl implements InitInfoService {

	private static JsonMapper mapper = new JsonMapper(Include.ALWAYS);

	private static final String SERVICEURL = "serviceUrl";

	public void initMaxPointCode() {
		try {
			Map<String, Object> paramsMapUserInfo = Maps.newHashMap();
			StringBuilder url = new StringBuilder();
			url.append(PropertiesUtil.getProperty(SERVICEURL));
			url.append("getMAXPointCode");
			String code = HttpClientUtil.callUrlPost(url.toString(), paramsMapUserInfo);
			ResponseDTO responseDTO = mapper.fromJson(code, ResponseDTO.class);
			WechatMessage.setMaxPointCode(responseDTO.getData().get("maxPointCode").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
