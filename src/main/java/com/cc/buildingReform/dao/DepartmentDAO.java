package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Department;

@Repository
public class DepartmentDAO extends CcHibernateDao<Department, String> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findAll() {
		Criteria criteria = getSession().createCriteria(Department.class);
		
		criteria.addOrder(Order.asc("length"));
		criteria.addOrder(Order.asc("id"));
		
		return (List<Department>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(Department.class);
		
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
	public List<Department> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Department.class);
		
		criteria.addOrder(Order.asc("length"));
		criteria.addOrder(Order.asc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Department>) criteria.list();
	}
	
	/**
	 * 查找指定父id的所有机构 2016-06-25 by p
	 * 
	 * @param fatherId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findByFatherId(String fatherId) {
		Criteria criteria = getSession().createCriteria(Department.class);
		
		criteria.add(Restrictions.eq("fatherId", fatherId));
		
		criteria.addOrder(Order.asc("length"));
		criteria.addOrder(Order.asc("id"));
		
		return (List<Department>) criteria.list();
	}
	
	/**
	 * 查询指定范围的机构 2016-07-18 by p
	 * 
	 * @param beginCode
	 * @param length
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findByRange(String beginCode, List<Integer> length) {
		Criteria criteria = getSession().createCriteria(Department.class);
		
		criteria.add(Restrictions.ilike("id", beginCode, MatchMode.START));
		criteria.add(Restrictions.in("length", length));
		
		criteria.addOrder(Order.asc("length"));
		criteria.addOrder(Order.asc("id"));
		
		return (List<Department>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Department> findByIsStatistics(List<String> id, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Department.class);
		
		criteria.add(Restrictions.eq("isStatistics", 1));
		criteria.add(Restrictions.not(Restrictions.in("id", id)));
		
		criteria.addOrder(Order.asc("length"));
		criteria.addOrder(Order.asc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Department>) criteria.list();
	}
}
