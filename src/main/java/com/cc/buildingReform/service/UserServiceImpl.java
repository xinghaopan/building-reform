package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.UserDAO;
import com.cc.buildingReform.form.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public void save(User user) {
		userDAO.saveOrUpdate(user);
	}

	public void remove(Integer id) {
		userDAO.delete(id);
	}
	
	public User findById(Integer id) {
		return userDAO.get(id);
	}

	public User findByName(Integer id, String name) {
		return userDAO.findByName(id, name);
	}
	
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	public int getCount() {
		return userDAO.getCount();
	}

	public List<User> findAll(int firstResult, int maxResult) {
		return userDAO.findAll(firstResult, maxResult);
	}
	
	public int getCount(Integer roleId, String departmentId, String name, String trueName) {
		return userDAO.getCount(roleId, departmentId, name, trueName);
	}
	
	public List<User> search(Integer roleId, String departmentId, String name, String trueName, int firstResult, int maxResult) {
		return userDAO.search(roleId, departmentId, name, trueName, firstResult, maxResult);
	}
	
	public User findByKey(String key) {
		return userDAO.findByKey(key);
	}
}
