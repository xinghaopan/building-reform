package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Key;


public interface KeyService {

	public void save(Key key);

	public void remove(Integer id);
	
	public Key findById(Integer id);

	public List<Key> findAll();
}