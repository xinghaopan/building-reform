package com.cc.buildingReform.service;

import java.util.List;

import com.cc.buildingReform.form.StatisticsQuota;

public interface StatisticsQuotaService {

	public List<StatisticsQuota> findByDepartmentId(String departmentId, Integer year);
	
	public List<StatisticsQuota> findByQuotaManageId(String quotaManageId, Integer year);
}