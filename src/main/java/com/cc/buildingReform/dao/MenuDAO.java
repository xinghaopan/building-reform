package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Menu;

@Repository
public class MenuDAO extends CcHibernateDao<Menu, Integer> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findAll() {
		Criteria criteria = getSession().createCriteria(Menu.class);
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Menu>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(Menu.class);
		
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
	public List<Menu> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Menu.class);
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Menu>) criteria.list();
	}
	
	/**
	 * 查找指定父id的所有菜单 2016-06-25 by p
	 * 
	 * @param fatherId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findByFatherId(Integer fatherId) {
		Criteria criteria = getSession().createCriteria(Menu.class);
		
		criteria.add(Restrictions.eq("fatherId", fatherId));
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Menu>) criteria.list();
	}
	
	/**
	 * 查找指定专题id的所有菜单 2016-06-25 by p
	 * 
	 * @param isSubject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findBySubject(Integer isSubject) {
		Criteria criteria = getSession().createCriteria(Menu.class);
		
		criteria.add(Restrictions.eq("isSubject", isSubject));
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Menu>) criteria.list();
	}

	/**
	 * 查找导航菜单  2016-06-25 by p
	 * 
	 * @param id
	 * @param isNavigation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findByNavigation(Integer fatherId, Integer isNavigation) {
		Criteria criteria = getSession().createCriteria(Menu.class);
		
		criteria.add(Restrictions.eq("fatherId", fatherId));
		criteria.add(Restrictions.eq("isNavigation", isNavigation));
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Menu>) criteria.list();
	}
}
