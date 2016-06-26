package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.MenuDAO;
import com.cc.buildingReform.form.Menu;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;

	public void save(Menu menu) {
		menuDAO.saveOrUpdate(menu);
	}

	public void remove(Integer id) {
		menuDAO.delete(id);
	}
	
	public Menu findById(Integer id) {
		return menuDAO.get(id);
	}

	public List<Menu> findAll() {
		return menuDAO.findAll();
	}
	
	public List<Menu> findByFatherId(Integer fatherId) {
		return menuDAO.findByFatherId(fatherId);
	}
	
	public Menu findFather(Integer id) {
		if (id != 0) {
			Menu r = findById(id);
			while (r != null && r.getFatherId() != 0) {
				r = findById(r.getFatherId());
			}
			
			return r;
		}
		else {
			return null;
		}
	}
	
	public List<Menu> findBySubject(Integer isSubject) {
		return menuDAO.findBySubject(isSubject);
	}
	
	public List<Menu> findByNavigation(Integer id, Integer isNavigation) {
		return menuDAO.findByNavigation(id, isNavigation);
	}
}
