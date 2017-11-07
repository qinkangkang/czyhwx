package com.czyh.czyhwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TCustomerLevel;

public interface CustomerLevelDAO
		extends JpaRepository<TCustomerLevel, String>, JpaSpecificationExecutor<TCustomerLevel> {

	@Query("select t from TCustomerLevel t")
	List<TCustomerLevel> getByCustomerLevelList();
	
	@Query("select t from TCustomerLevel t where t.flevel = ?1")
	TCustomerLevel getByLevel(Integer level);

}