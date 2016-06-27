package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.DepartmentDAO;
import com.cc.buildingReform.form.Department;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;

	public void save(Department department) {
		departmentDAO.saveOrUpdate(department);
	}

	public void remove(Integer id) {
		departmentDAO.delete(id);
	}
	
	public Department findById(Integer id) {
		return departmentDAO.get(id);
	}

	public List<Department> findAll() {
		return departmentDAO.findAll();
	}
	
	public List<Department> findByFatherId(Integer fatherId) {
		return departmentDAO.findByFatherId(fatherId);
	}
	
	public Department findFather(Integer id) {
		if (id != 0) {
			Department r = findById(id);
			while (r != null && r.getFatherId() != 0) {
				r = findById(r.getFatherId());
			}
			
			return r;
		}
		else {
			return null;
		}
	}
}
