package com.cc.buildingReform.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.Info;

@Repository
public class InfoDAO extends CcHibernateDao<Info, Integer> {
	public int maintain(Info info) {
		SQLQuery query = getSession().createSQLQuery("");
		query.setInteger(0, info.getId() == null ? 0 : info.getId());
		List list = query.list();
		if (list == null || list.size() == 0) {
			return -100;
		}
		return Integer.parseInt(list.get(0).toString());
	}

	public void statisticsQuota(Integer year) {
		SQLQuery query = getSession().createSQLQuery("{CALL PRO_STATISTICS_QUOTA(?)}");
		query.setInteger(0, year);
		query.executeUpdate();
	}
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
	
	@SuppressWarnings("unchecked")
	public List<Info> findByDepartmentId(Integer year, Integer state, List<String> departmentIdList) {
		Criteria criteria = getSession().createCriteria(Info.class);
		
		criteria.add(Restrictions.eq("planYear", year));
		if (state != null) {
			criteria.add(Restrictions.eq("state", state));
		}
		criteria.add(Restrictions.in("departmentId", departmentIdList));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<Info>) criteria.list();
		
	}
	
	/**
	 * 根据上报信息状态和所属机构查询上报信息（数量） 2016-07-19 by p
	 * 
	 * @param state
	 * @param departmentIdList
	 * @return
	 */
	public int getCountByDepartmentId(Integer year, Integer state, List<String> departmentIdList) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null) {
			criteria.add(Restrictions.eq("state", state));
		}
		criteria.add(Restrictions.in("departmentId", departmentIdList));

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountByDepartmentId(Integer year, Integer state, List<String> departmentIdList, String personName, String personId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null) {
			criteria.add(Restrictions.eq("state", state));
		}
		criteria.add(Restrictions.in("departmentId", departmentIdList));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	/**
	 * 根据上报信息状态和所属机构查询上报信息（分页） 2016-07-19 by p
	 * 
	 * @param state
	 * @param departmentIdList
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Info> findByDepartmentId(Integer year, Integer state, List<String> departmentIdList, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null) {
			criteria.add(Restrictions.eq("state", state));
		}
		criteria.add(Restrictions.in("departmentId", departmentIdList));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Info> findByDepartmentId(Integer year, Integer state, List<String> departmentIdList, int firstResult, int maxResult, String personName, String personId) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null) {
			criteria.add(Restrictions.eq("state", state));
		}
		criteria.add(Restrictions.in("departmentId", departmentIdList));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();

	}

	public int getCountByDepartmentId(Integer year, List<Integer> state, String departmentIdList) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null && !state.isEmpty()) {
			criteria.add(Restrictions.in("state", state));
		}
		criteria.add(Restrictions.eq("departmentId", departmentIdList));

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountByDepartmentId(Integer year, List<Integer> state, String departmentIdList, String personName, String personId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null && !state.isEmpty()) {
			criteria.add(Restrictions.in("state", state));
		}
		criteria.add(Restrictions.eq("departmentId", departmentIdList));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByDepartmentId(Integer year, List<Integer> state, String departmentIdList, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null && !state.isEmpty()) {
			criteria.add(Restrictions.in("state", state));
		}
		criteria.add(Restrictions.eq("departmentId", departmentIdList));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (maxResult > 0) {
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
		}

		return (List<Info>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByDepartmentId(Integer year, List<Integer> state, String departmentIdList, int firstResult, int maxResult, String personName, String personId) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		if (state != null && !state.isEmpty()) {
			criteria.add(Restrictions.in("state", state));
		}
		criteria.add(Restrictions.eq("departmentId", departmentIdList));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();

	}

	public int getCountByAuditDepartmentId(Integer year, String departmentId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("auditDepartmentId", departmentId));
		criteria.add(Restrictions.eq("state", departmentId.length() * 10));
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountByAuditDepartmentId(Integer year, String auditDepartmentId, String personName, String personId, String departmentId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("auditDepartmentId", auditDepartmentId));
		criteria.add(Restrictions.eq("state", auditDepartmentId.length() * 10));
		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		if (departmentId != null && !departmentId.isEmpty()) {
			criteria.add(Restrictions.ilike("sonDepartmentId", departmentId, MatchMode.START));
		}

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByAuditDepartmentId(Integer year, String departmentId, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("auditDepartmentId", departmentId));
		criteria.add(Restrictions.eq("state", departmentId.length() * 10));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (maxResult > 0) {
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
		}

		return (List<Info>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByAuditDepartmentId(Integer year, String auditDepartmentId, int firstResult, int maxResult, String personName, String personId, String departmentId) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("auditDepartmentId", auditDepartmentId));
		criteria.add(Restrictions.eq("state", auditDepartmentId.length() * 10));
		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		if (departmentId != null && !departmentId.isEmpty()) {
			criteria.add(Restrictions.ilike("sonDepartmentId", departmentId, MatchMode.START));
		}

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();

	}

	public int getCountByManageDepartmentId(Integer year, List<String> departmentId, String personName, String personId, String property1, String property2) {
		Criteria criteria = this.getSession().createCriteria(Info.class);
		
		criteria.add(Restrictions.eq("planYear", year));

		Disjunction disjunction = Restrictions.disjunction();
		for (int i = 0; i < departmentId.size(); i ++) {
			disjunction.add(Restrictions.like("sonDepartmentId", departmentId.get(i), MatchMode.START));
		}
		criteria.add(disjunction);

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}
		
		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}
		
		Date now = new Date();
		
		// 第一个日期字段不为空
		if (property1 != null) {
			criteria.add(Restrictions.le(property1, now));
		}
		
		// 第二个日期字段不为空
		if (property2 != null) {
			// 日期为空，或大于当前时间
			disjunction = Restrictions.disjunction();
			disjunction.add(Property.forName(property2).isNull());
			disjunction.add(Restrictions.gt(property2, now));
			criteria.add(disjunction);
		}
		
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<Info> findByManageDepartmentId(Integer year, List<String> departmentId, String personName, String personId, String property1, String property2, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);
		
		criteria.add(Restrictions.eq("planYear", year));
		Disjunction disjunction = Restrictions.disjunction();
		for (int i = 0; i < departmentId.size(); i ++) {
			disjunction.add(Restrictions.like("sonDepartmentId", departmentId.get(i), MatchMode.START));
		}
		criteria.add(disjunction);
		
		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}
		
		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}
		
		Date now = new Date();
		
		// 第一个日期字段不为空
		if (property1 != null) {
			criteria.add(Restrictions.le(property1, now));
		}
		
		// 第二个日期字段不为空
		if (property2 != null) {
			// 日期为空，或大于当前时间
			disjunction = Restrictions.disjunction();
			disjunction.add(Property.forName(property2).isNull());
			disjunction.add(Restrictions.gt(property2, now));
			criteria.add(disjunction);
		}
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<Info>) criteria.list();
		
	}

	public int getCountByDate(Integer year, String departmentId, String propertyName) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Property.forName(propertyName).isNull());

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountByDate(Integer year, String departmentId, String propertyName, String personName, String personId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Property.forName(propertyName).isNull());

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByDate(Integer year, String departmentId, String propertyName, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Property.forName(propertyName).isNull());

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (maxResult > 0) {
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
		}

		return (List<Info>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByDate(Integer year, String departmentId, String propertyName, int firstResult, int maxResult, String personName, String personId) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Property.forName(propertyName).isNull());

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();

	}

	public int getCountByAcceptanceInfo(Integer year, String departmentId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Restrictions.lt("acceptanceDate", new Date()));

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	public int getCountByAcceptanceInfo(Integer year, String departmentId, String personName, String personId) {
		Criteria criteria = this.getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Restrictions.lt("acceptanceDate", new Date()));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByAcceptanceInfo(Integer year, String departmentId, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(Info.class);
		
		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Restrictions.lt("acceptanceDate", new Date()));
		
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (maxResult > 0) {
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
		}

		return (List<Info>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Info> findByAcceptanceInfo(Integer year, String departmentId, int firstResult, int maxResult, String personName, String personId) {
		Criteria criteria = getSession().createCriteria(Info.class);

		criteria.add(Restrictions.eq("planYear", year));
		criteria.add(Restrictions.eq("departmentId", departmentId));
		criteria.add(Restrictions.eq("state", Info.STATE_OVER));
		criteria.add(Restrictions.lt("acceptanceDate", new Date()));

		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));

		if (personName != null && !personName.isEmpty()) {
			criteria.add(Restrictions.ilike("personName", personName, MatchMode.ANYWHERE));
		}

		if (personId != null && !personId.isEmpty()) {
			criteria.add(Restrictions.ilike("personId", personId, MatchMode.ANYWHERE));
		}

		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);

		return (List<Info>) criteria.list();
	}
}
