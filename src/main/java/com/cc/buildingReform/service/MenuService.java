package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Menu;


public interface MenuService {

	public void save(Menu menu);

	public void remove(Integer id);
	
	public Menu findById(Integer id);

	public List<Menu> findAll();
	
	public Menu findFather(Integer id);

	public List<Menu> findByFatherId(Integer fatherId);
	
	public List<Menu> findBySubject(Integer isSubject);
	
	public List<Menu> findByNavigation(Integer id, Integer isNavigation);
	
}