package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Dic;

@Repository
public class DicDAO extends CcHibernateDao<Dic, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Dic> findAll() {
		Criteria criteria = getSession().createCriteria(Dic.class);
		
		criteria.addOrder(Order.asc("keyName"));
		criteria.addOrder(Order.desc("value"));
		
		return (List<Dic>) criteria.list();
	}
	
	/**
	 * 查找指定父id的所有菜单 2016-06-25 by p
	 * 
	 * @param fatherId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Dic> findByKeyId(Integer keyId) {
		Criteria criteria = getSession().createCriteria(Dic.class);
		
		criteria.add(Restrictions.eq("keyId", keyId));
		
		criteria.addOrder(Order.asc("keyName"));
		criteria.addOrder(Order.desc("value"));
		
		return (List<Dic>) criteria.list();
	}
	
}
