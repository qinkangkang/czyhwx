package com.czyh.czyhwx.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.czyh.czyhwx.entity.TWxMenuItem;

public interface WxMenuItemDAO extends JpaRepository<TWxMenuItem, Long>, JpaSpecificationExecutor<TWxMenuItem> {

	@Query("select t from TWxMenuItem t where t.TWxMenu.id=?1")
	List<TWxMenuItem> getMenuList(Long menuId);

}