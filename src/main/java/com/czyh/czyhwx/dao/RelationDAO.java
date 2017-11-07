package com.czyh.czyhwx.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TRelation;

public interface RelationDAO extends JpaRepository<TRelation, String>, JpaSpecificationExecutor<TRelation> {

	@Query("select t from TRelation t where t.fcustomerId = ?1 and t.fbyCustomerId = ?2")
	TRelation findByUser(String fcustomerId, String fbyCustomerId);
}