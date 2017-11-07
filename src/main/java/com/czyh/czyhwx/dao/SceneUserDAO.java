package com.czyh.czyhwx.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TSceneUser;

public interface SceneUserDAO extends JpaRepository<TSceneUser, String>, JpaSpecificationExecutor<TSceneUser> {

	@Query("select t from TSceneUser t where t.fopenId = ?1 and t.fsubscribe = 1")
	TSceneUser findSceneUserSub(String openid);

	@Query("select t from TSceneUser t where t.fopenId = ?1 and t.fsubscribe = 0")
	TSceneUser findSceneUserUnSub(String openid);

	@Modifying
	@Query("update TSceneUser t set t.fsubscribe = ?2, t.funSubscribe = ?3,t.funSubscribeTime = ?4 where t.id = ?1")
	void setUnSubscribe(String id, int i, int j, Date date);

	@Query("select t from TSceneUser t where t.fopenId = ?1 ")
	TSceneUser findOneByOpenid(String openid);

	@Modifying
	@Query("update TSceneUser t set t.fsceneGps = ?1 where t.id = ?2")
	void saveGps(String gps, String string);

	@Modifying
	@Query("update TSceneUser t set t.fdelivery = ?2,t.fdeliveryTime = ?3 where t.fopenId = ?1")
	void updateDelivery(String openid, int i, Date now);

	@Modifying
	@Query("update TSceneUser t set t.fregister = ?2,t.fregisterTime = ?3 where t.fopenId = ?1")
	void updateRegister(String openid, int i, Date now);

	@Modifying
	@Query("update TSceneUser t set t.fsubscribe = ?2, t.funSubscribe = ?3 where t.fopenId = ?1")
	void setSubscribeAgain(String id, int fsubscribe, int funSubscribe);

	@Query("select t from TSceneUser t where t.fopenId = ?1 and t.fsubscribe = 0 ")
	TSceneUser findSnSubscribe(String openid);

	@Modifying
	@Query("update TSceneUser t set t.fbounsCustomer = ?2 where t.fopenId = ?1")
	void setBounsCustomer(String openid, int bouns);

	@Modifying
	@Query("update TSceneUser t set t.fsceneStr = ?2 where t.fopenId = ?1")
	void setfSceneStr(String openid, String str);

	@Query("select t from TSceneUser t where t.fopenId = ?1")
	TSceneUser findBySub(String openid);

	@Query("select t from TSceneUser t where t.fsubscribeTime between '2016-03-01 00:00:00' and '2016-10-13 23:59:59' and t.fsubscribe = 0 and t.funSubscribe= 1")
	List<TSceneUser> test();

	@Modifying
	@Query("update TSceneUser t set t.fcity = ?2, t.flocation = ?3 where t.fopenId = ?1")
	void updateCityName(String openid, String city, String location);

}