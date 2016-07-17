package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Quota;


public interface QuotaService {

	public void save(Quota quota);

	public void remove(Integer id);
	
	public Quota findById(Integer id);

	public List<Quota> findAll(Integer year);
	
	public int getCount(Integer year);
	
	public List<Quota> findAll(Integer year, int firstResult, int maxResult);
	
	public List<Quota> findByDepartmentId(Integer year, String departmentId);

	public List<Integer> findExistYear();
}