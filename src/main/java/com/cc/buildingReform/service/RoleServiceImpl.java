package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.RoleDAO;
import com.cc.buildingReform.form.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	public void save(Role role) {
		roleDAO.saveOrUpdate(role);
	}

	public void remove(Integer id) {
		roleDAO.delete(id);
	}
	
	public Role findById(Integer id) {
		return roleDAO.get(id);
	}

	public List<Role> findAll() {
		return roleDAO.findAll();
	}
	
	public int getCount() {
		return roleDAO.getCount();
	}

	public List<Role> findAll(int firstResult, int maxResult) {
		return roleDAO.findAll(firstResult, maxResult);
	}
}
