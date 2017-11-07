package com.czyh.czyhwx.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TCustomerBargaining;

public interface CustomerBargainingDAO
		extends JpaRepository<TCustomerBargaining, String>, JpaSpecificationExecutor<TCustomerBargaining> {
	
	@Query("select t from TCustomerBargaining t where t.fcustomerId = ?1 and t.fbargainingId = ?2")
	TCustomerBargaining getByCustomerId(String fCustomerBargainingId,String fBargainingId);
	
	@Query("select t from TCustomerBargaining t where t.fbargainingNum = ?1 ")
	TCustomerBargaining getByBargainingNum(String bargainingNum);

}