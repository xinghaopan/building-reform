package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Quota;
import com.cc.buildingReform.form.User;


public interface QuotaService {

	public void save(Quota quota);

	public void saveDistribute(Quota quota, User user);
	
	public void remove(Integer id);
	
	public Quota findById(Integer id);

	public List<Quota> findAll(Integer year);
	
	public int getCount(Integer year);
	
	public List<Quota> findAll(Integer year, int firstResult, int maxResult);
	
	public List<Quota> findByDepartmentId(Integer year, String departmentId);

	public List<Integer> findExistYear();
	
	public List<Quota> findByFatherDepartmentId(Integer year, String fatherDepartmentId);
	
	public List<Quota> findByDistributeId(String distributeId, Integer year);
	
	public List<Quota> yearsStatistics(Integer year, Integer num);
}