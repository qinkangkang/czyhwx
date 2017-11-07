package com.czyh.czyhwx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.czyh.czyhwx.entity.TCustomerLog;


public interface CustomerLogDAO extends JpaRepository<TCustomerLog, Long>, JpaSpecificationExecutor<TCustomerLog> {

}