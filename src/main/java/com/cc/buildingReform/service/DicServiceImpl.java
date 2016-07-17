package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.DicDAO;
import com.cc.buildingReform.form.Dic;

@Service
@Transactional
public class DicServiceImpl implements DicService {

	@Autowired
	private DicDAO dicDAO;

	public void save(Dic dic) {
		dicDAO.saveOrUpdate(dic);
	}

	public void remove(Integer id) {
		dicDAO.delete(id);
	}
	
	public Dic findById(Integer id) {
		return dicDAO.get(id);
	}

	public List<Dic> findAll() {
		return dicDAO.findAll();
	}
	
	public List<Dic> findByKeyId(Integer keyId) {
		return dicDAO.findByKeyId(keyId);
	}
}
