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
		int len = 0;
		if (department.getFatherId().equals("00")) {
			len = 2;
		}
		else {
			len = department.getFatherId().length() + 2;
		}
		department.setLength(len);
		departmentDAO.saveOrUpdate(department);
	}

	public void remove(String id) {
		departmentDAO.delete(id);
	}
	
	public Department findById(String id) {
		return departmentDAO.get(id);
	}

	public List<Department> findAll() {
		return departmentDAO.findAll();
	}
	
	public List<Department> findByFatherId(String fatherId) {
		return departmentDAO.findByFatherId(fatherId);
	}
	
	public Department findFather(String id) {
		if (id != null) {
			Department r = findById(id);
			while (r != null && r.getFatherId() != "00") {
				r = findById(r.getFatherId());
			}
			
			return r;
		}
		else {
			return null;
		}
	}
	
	public List<Department> findByRange(String beginCode, List<Integer> length) {
		return departmentDAO.findByRange(beginCode, length);
	}
}
