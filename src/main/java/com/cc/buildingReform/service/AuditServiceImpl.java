package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.AuditDAO;
import com.cc.buildingReform.form.Audit;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDAO AuditDAO;

	public void save(Audit Audit) {
		AuditDAO.saveOrUpdate(Audit);
	}

	public void remove(Integer id) {
		AuditDAO.delete(id);
	}
	
	public List<Audit> findByInfoId(Integer infoId) {
		return AuditDAO.findByInfoId(infoId);
	}
}
