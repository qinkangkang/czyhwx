package com.czyh.czyhwx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TCarnival;


public interface CarnivalDAO extends JpaRepository<TCarnival, String>, JpaSpecificationExecutor<TCarnival> {
	
	@Modifying
	@Query("update TCarnival t set t.fstatus = ?1 where t.id = ?2")
	void delCarnival(Integer status, String id);

	@Modifying
	@Query("update TCarnival t set t.fstatus = ?1 where t.id = ?2")
	void saveStatusCarnival(Integer status, String id);

	@Query("from TCarnival t where t.fsceneStr = ?1")
	TCarnival findSceneStr(String sceneStr);
}