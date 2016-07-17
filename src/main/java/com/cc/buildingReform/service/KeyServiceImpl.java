package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.KeyDAO;
import com.cc.buildingReform.form.Key;

@Service
@Transactional
public class KeyServiceImpl implements KeyService {

	@Autowired
	private KeyDAO keyDAO;

	public void save(Key key) {
		keyDAO.saveOrUpdate(key);
	}

	public void remove(Integer id) {
		keyDAO.delete(id);
	}
	
	public Key findById(Integer id) {
		return keyDAO.get(id);
	}

	public List<Key> findAll() {
		return keyDAO.findAll();
	}
}
