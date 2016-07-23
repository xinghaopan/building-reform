package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Department;
import com.cc.buildingReform.form.User;


public interface DepartmentService {

	public void save(Department department);

	public void remove(String id);
	
	public Department findById(String id);

	public List<Department> findAll();
	
	public Department findFather(String id);

	public List<Department> findByFatherId(String fatherId);
	
	public List<Department> findByRange(String beginCode, List<Integer> length);
	
	public List<Department> findWaitDistribute(User user);
}