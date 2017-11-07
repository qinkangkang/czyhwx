package com.czyh.czyhwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TWxMenu;

public interface WxMenuDAO extends JpaRepository<TWxMenu, Long>, JpaSpecificationExecutor<TWxMenu> {

	@Query("select t from TWxMenu t where 1=1 order by t.menuOrder desc")
	List<TWxMenu> getMenuList();

}