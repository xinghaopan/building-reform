package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Quota;

@Repository
public class QuotaDAO extends CcHibernateDao<Quota, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Quota> findAll(Integer year) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.asc("id"));
		
		return (List<Quota>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount(Integer year) {
		Criteria criteria = this.getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	/**
	 * 查询所有信息(分页)  2016-06-25 by p
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Quota> findAll(Integer year, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.asc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Quota>) criteria.list();
	}
	
	/**
	 * 查找指部门id的指标 2016-06-25 by p
	 * 
	 * @param fatherId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Quota> findByDepartmentId(Integer year, String departmentId) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Quota>) criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Quota> findByDepartmentId(Integer year, List<String> departmentId) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.in("departmentId", departmentId));
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Quota>) criteria.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Integer> findExistYear() {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("year").as("year"));
		
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.desc("year"));
		return (List<Integer>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quota> findByFatherDepartmentId(Integer year, String fatherDepartmentId) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		
		Disjunction disjunction = Restrictions.disjunction();  
		disjunction.add(Restrictions.eq("departmentId", fatherDepartmentId));
		disjunction.add(Restrictions.eq("distributeDepartmentId", fatherDepartmentId));
		
		criteria.add(disjunction);
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Quota>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quota> findByDistributeId(String distributeId, Integer year) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("distributeDepartmentId", distributeId));
		criteria.add(Restrictions.eq("year", year));
		
		criteria.addOrder(Order.asc("departmentId"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Quota>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Quota> yearsStatistics(Integer year) {
		Criteria criteria = getSession().createCriteria(Quota.class);
		
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("departmentIsStatistics", 1));
		
		return (List<Quota>) criteria.list();
	}
}
