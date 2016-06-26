package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Role;

public interface RoleService {

	public void save(Role role);

	public void remove(Integer id);
	
	public Role findById(Integer id);
	
	public List<Role> findAll();
	
	public int getCount();

	public List<Role> findAll(int firstResult, int maxResult);
}