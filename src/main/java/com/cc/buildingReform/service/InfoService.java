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
	
	public void submit(User user, Integer id, String content);
	
	public void back(User user, Integer id, String content);
	
	public int getCountByWaitAudit(Integer year, User user);
	
	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult);
	
	public int getCountByAuditInfo(Integer year, User user);
	
	public List<Info> findByAuditInfo(Integer year, User user, int firstResult, int maxResult);
	
	public int getCountByBackInfo(Integer year, User user);
	
	public List<Info> findByBackInfo(Integer year, User user, int firstResult, int maxResult);
}