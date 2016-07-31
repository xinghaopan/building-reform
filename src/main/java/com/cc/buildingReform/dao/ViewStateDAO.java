package com.cc.buildingReform.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.ViewState;

@Repository
public class ViewStateDAO extends CcHibernateDao<ViewState, String> {
	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ViewState> findAll() {
		Criteria criteria = getSession().createCriteria(ViewState.class);
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<ViewState>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(ViewState.class);
		
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
	public List<ViewState> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(ViewState.class);
		
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("id"));
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<ViewState>) criteria.list();
	}
	
	public List<Object[]> findByFatherId(Integer year, String fatherId, Integer length) {
		String sql = "select LEFT(state_id, " + length + ") as state_id, state_year, "
				+ "sum(state_begin_num) as state_begin_num, sum(state_end_num) as state_end_num, "
				+ "sum(state_acceptance_num) as state_acceptance_num, sum(state_fund_send_num) as state_fund_send_num, "
				+ "sum(state_sum) as state_sum from br_view_state "
				+ "where state_year = " + year + " and state_id like '" + fatherId + "%'"
				+ "group by LEFT(state_id, " + length + "), state_year ";
		
		return super.listSql(sql);
	}
}
