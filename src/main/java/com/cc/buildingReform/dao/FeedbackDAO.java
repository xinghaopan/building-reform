package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Feedback;

@Repository
public class FeedbackDAO extends CcHibernateDao<Feedback, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Feedback> findAll() {
		Criteria criteria = getSession().createCriteria(Feedback.class);
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Feedback>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(Feedback.class);
		
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
	public List<Feedback> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Feedback.class);
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Feedback>) criteria.list();
	}
	
	/**
	 * 查询指定用户的反馈信息（数量） 2016-08-01 by p
	 * 
	 * @param userId
	 * @return
	 */
	public int getCountByUserId(Integer userId) {
		Criteria criteria = this.getSession().createCriteria(Feedback.class);
		criteria.add(Restrictions.eq("userId", userId));
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	/**
	 * 查询指定用户的反馈信息（分页) 2016-08-01 by p
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Feedback> findByUserId(Integer userId, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Feedback.class);
		
		criteria.add(Restrictions.eq("userId", userId));
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Feedback>) criteria.list();
	}
	
}
