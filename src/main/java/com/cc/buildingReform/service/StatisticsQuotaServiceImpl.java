package com.cc.buildingReform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.buildingReform.dao.StatisticsQuotaDAO;
import com.cc.buildingReform.form.StatisticsQuota;

@Service
@Transactional
public class StatisticsQuotaServiceImpl implements StatisticsQuotaService {

	@Autowired
	private StatisticsQuotaDAO statisticsQuotaDAO;

	public List<StatisticsQuota> findByDepartmentId(String departmentId, Integer year) {
		return statisticsQuotaDAO.findByDepartmentId(departmentId, year);
	}
	
	public List<StatisticsQuota> findByQuotaManageId(String quotaManageId, Integer year) {
		return statisticsQuotaDAO.findByQuotaManageId(quotaManageId, year);
	}
}
