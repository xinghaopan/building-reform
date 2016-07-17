package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Info;

@Repository
public class InfoDAO extends CcHibernateDao<Info, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Info> findAll() {
		Criteria criteria = getSession().createCriteria(Info.class);
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Info>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(Info.class);
		
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
	public List<Info> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Info>) criteria.list();
	}
}
