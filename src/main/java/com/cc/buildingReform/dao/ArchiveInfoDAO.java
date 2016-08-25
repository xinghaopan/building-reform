package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.ArchiveInfo;

@Repository
public class ArchiveInfoDAO extends CcHibernateDao<ArchiveInfo, Integer> {
	
	public int getCountByDepartmentId(Integer year, String departmentId, String idcard) {
		Criteria criteria = this.getSession().createCriteria(ArchiveInfo.class);
		
		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.like("sonDepartmentId", departmentId, MatchMode.START));
		if (idcard != null && idcard != "") {
			criteria.add(Restrictions.like("personId", idcard, MatchMode.ANYWHERE));
		}
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<ArchiveInfo> findByDepartmentId(Integer year, String departmentId, String idcard, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(ArchiveInfo.class);
		
		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.like("sonDepartmentId", departmentId, MatchMode.START));
		if (idcard != null && idcard != "") {
			criteria.add(Restrictions.like("personId", idcard, MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<ArchiveInfo>) criteria.list();
		
	}
}
