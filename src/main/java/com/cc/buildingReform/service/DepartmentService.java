package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Department;


public interface DepartmentService {

	public void save(Department department);

	public void remove(Integer id);
	
	public Department findById(Integer id);

	public List<Department> findAll();
	
	public Department findFather(Integer id);

	public List<Department> findByFatherId(Integer fatherId);
}