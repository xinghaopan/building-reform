package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Key;

@Repository
public class KeyDAO extends CcHibernateDao<Key, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Key> findAll() {
		Criteria criteria = getSession().createCriteria(Key.class);
		
		criteria.addOrder(Order.asc("name"));
		
		return (List<Key>) criteria.list();
	}
}
