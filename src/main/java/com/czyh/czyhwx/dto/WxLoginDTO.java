package com.czyh.czyhwx.dto;

import java.io.Serializable;

import com.czyh.czyhwx.util.NullToEmptySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WxLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int clientType = 0;
	
	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String unionid;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String openid;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String nickname;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String headimgurl;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String country;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String province;

	@JsonSerialize(nullsUsing = NullToEmptySerializer.class)
	private String city;
	
	private int sex;
	
	private boolean subscribe = false;

	
	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

}