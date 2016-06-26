package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.User;

@Repository
public class UserDAO extends CcHibernateDao<User, Integer> {

	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		Criteria criteria = getSession().createCriteria(User.class);
		
		criteria.addOrder(Order.desc("id"));
		
		return (List<User>) criteria.list();
	}
	
	/**
	 * 查找相同用户名的用户(不包括自己) 2016-06-25 by p
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User findByName(Integer id, String name) {
		Criteria criteria = getSession().createCriteria(User.class);
		
		if (id != null) {
			criteria.add(Restrictions.ne("id", id));
		}
		criteria.add(Restrictions.eq("name", name));
		
		List<User> l = (List<User>) criteria.list();
		
		if(l != null && l.size() > 0) {
			return l.get(0);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(User.class);
		
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
	public List<User> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(User.class);
		
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<User>) criteria.list();
	}
}
