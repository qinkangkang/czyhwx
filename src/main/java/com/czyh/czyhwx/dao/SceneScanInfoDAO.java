package com.czyh.czyhwx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.czyh.czyhwx.entity.TSceneScanInfo;


public interface SceneScanInfoDAO
		extends JpaRepository<TSceneScanInfo, String>, JpaSpecificationExecutor<TSceneScanInfo> {

}