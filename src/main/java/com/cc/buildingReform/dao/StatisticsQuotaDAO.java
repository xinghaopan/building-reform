package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.StatisticsQuota;

@Repository
public class StatisticsQuotaDAO extends CcHibernateDao<StatisticsQuota, String> {
	/**
	 * 查询指定机构负责指标管理的所有机构 2016-09-17 by p
	 * 
	 * @param quotaManageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsQuota> findByDepartmentId(String departmentId, Integer year) {
		Criteria criteria = getSession().createCriteria(StatisticsQuota.class);
		
		criteria.add(Restrictions.eq("id", departmentId));
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("id"));
		
		return (List<StatisticsQuota>) criteria.list();
	}
	
	/**
	 * 查询指定机构负责指标管理的所有机构 2016-09-17 by p
	 * 
	 * @param quotaManageId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsQuota> findByQuotaManageId(String quotaManageId, Integer year) {
		Criteria criteria = getSession().createCriteria(StatisticsQuota.class);
		
		criteria.add(Restrictions.eq("quotaManageId", quotaManageId));
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("id"));
		
		return (List<StatisticsQuota>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<StatisticsQuota> findByStatistics(Integer year) {
		Criteria criteria = getSession().createCriteria(StatisticsQuota.class);
		
		criteria.add(Restrictions.eq("isStatistics", 1));
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("id"));
		
		return (List<StatisticsQuota>) criteria.list();
	}
}
