package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Audit;


public interface AuditService {

	public void save(Audit Audit);

	public void remove(Integer id);
	
	public List<Audit> findByInfoId(Integer infoId);
}