package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.Info;
import com.cc.buildingReform.form.User;

public interface InfoService {

	public void save(Info info);

	public void remove(Integer id);
	
	public Info findById(Integer id);
	
	public List<Info> findAll();
	
	public int getCount(Integer year, User user);
	
	public List<Info> findAll(Integer year, User user, int firstResult, int maxResult);
	
	public int getCountByEdit(Integer year, User user);
	
	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult);
	
	public void submit(User user, Integer id);
	
	public int getCountByWaitAudit(Integer year, User user);
	
	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult);
}