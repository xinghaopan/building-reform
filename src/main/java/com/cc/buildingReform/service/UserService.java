package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.User;

public interface UserService {

	public void save(User user);

	public void remove(Integer id);
	
	public User findById(Integer id);
	
	public User findByName(Integer id, String name);
	
	public List<User> findAll();
	
	public int getCount();

	public List<User> findAll(int firstResult, int maxResult);
}