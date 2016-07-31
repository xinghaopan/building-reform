package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Audit;

@Repository
public class AuditDAO extends CcHibernateDao<Audit, Integer> {
	/**
	 * 查询指定上报信息的审核信息 2016-07-31 by p
	 * 
	 * @param infoId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Audit> findByInfoId(Integer infoId) {
		Criteria criteria = getSession().createCriteria(Audit.class);
		
		criteria.add(Restrictions.eq("infoId", infoId));
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Audit>) criteria.list();
	}
	
}
