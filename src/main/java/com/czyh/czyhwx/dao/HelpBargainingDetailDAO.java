package com.czyh.czyhwx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.THelpBargainingDetail;

public interface HelpBargainingDetailDAO
		extends JpaRepository<THelpBargainingDetail, String>, JpaSpecificationExecutor<THelpBargainingDetail> {

	@Query("select count(t.id) from THelpBargainingDetail t where t.fhelperId = ?1")
	Long getDetailByHelper(String fHelperId);

	@Query("select count(t.id) from THelpBargainingDetail t where t.fbargainingId = ?1")
	Long getDetailByBargainId(String fHelperId);
}