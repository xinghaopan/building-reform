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

	public int getCountByEdit(Integer year, User user, String personName, String personId);

	public List<Info> findByEdit(Integer year, User user);

	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult);

	public List<Info> findByEdit(Integer year, User user, int firstResult, int maxResult, String personName, String personId);

	public void submit(User user, Integer id, String content);
	
	public void back(User user, Integer id, String content);
	
	public int getCountByWaitAudit(Integer year, User user);
	
	public List<Info> findByWaitAudit(Integer year, User user, int firstResult, int maxResult);
	
	public void batchSubmit(User user, String ids, String content);
	
	public void batchBack(User user, String ids, String content);

	public int getCountByAuditInfo(Integer year, User user, String personName, String personId);

	public int getCountByAuditInfo(Integer year, User user);

	public List<Info> findByAuditInfo(Integer year, User user, int firstResult, int maxResult);

	public List<Info> findByAuditInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId);

	public int getCountByBackInfo(Integer year, User user);

	public int getCountByBackInfo(Integer year, User user, String personName, String personId);

	public List<Info> findByBackInfo(Integer year, User user, int firstResult, int maxResult);

	public List<Info> findByBackInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId);

	public int checkId(Integer id, String idcard);
	
	public int getCountByDepartmentId(Integer year, String departmentId, String personName, String personId, Integer state);
	
	public List<Info> findByDepartmentId(Integer year, String departmentId, String personName, String personId, Integer state, int firstResult, int maxResult);

	public int getCountByNoOpen(Integer year, String departmentId);

	public int getCountByNoOpen(Integer year, String departmentId, String personName, String personId);

	public List<Info> findByNoOpen(Integer year, String departmentId, int firstResult, int maxResult);

	public List<Info> findByNoOpen(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId);

	public int getCountByNoOver(Integer year, String departmentId);

	public int getCountByNoOver(Integer year, String departmentId, String personName, String personId);

	public List<Info> findByNoOver(Integer year, String departmentId, int firstResult, int maxResult);

	public List<Info> findByNoOver(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId);

	public int getCountByNoAcceptance(Integer year, String departmentId);

	public int getCountByNoAcceptance(Integer year, String departmentId, String personName, String personId);

	public List<Info> findByNoAcceptance(Integer year, String departmentId, int firstResult, int maxResult);

	public List<Info> findByNoAcceptance(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId);

	public int getCountByAcceptanceInfo(Integer year, User user);

	public int getCountByAcceptanceInfo(Integer year, User user, String personName, String personId);

	public List<Info> findByAcceptanceInfo(Integer year, User user, int firstResult, int maxResult);

	public List<Info> findByAcceptanceInfo(Integer year, User user, int firstResult, int maxResult, String personName, String personId);

	public void archive(String ids, User user);
	
	public void statisticsQuota(Integer year);
}