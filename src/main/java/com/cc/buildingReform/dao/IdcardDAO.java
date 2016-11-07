package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Idcard;

@Repository
public class IdcardDAO extends CcHibernateDao<Idcard, Integer> {
	/**
	 * 校验身份证号 2016-08-21 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int checkId(Integer id, String idcard) {
		Criteria criteria = getSession().createCriteria(Idcard.class);

		// 身份证号校验，不需要判断id，只要判断这个号码是否存在
//		if (id != null) {
//			criteria.add(Restrictions.ne("id", id));
//		}
		criteria.add(Restrictions.eq("idcard", idcard.toUpperCase()));
		
		List<Idcard> list = (List<Idcard>) criteria.list();
		
		if (list != null && !list.isEmpty()) {
			return 0;
		} 
		else {
			return 1;
		}
	}
}
