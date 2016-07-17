package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Info;

public interface InfoService {

	public void save(Info info);

	public void remove(Integer id);
	
	public Info findById(Integer id);
	
	public List<Info> findAll();
	
	public int getCount();
	
	public List<Info> findAll(int firstResult, int maxResult);
}