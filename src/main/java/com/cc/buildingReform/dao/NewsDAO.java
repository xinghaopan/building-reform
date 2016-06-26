package com.cc.buildingReform.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cc.buildingReform.form.News;

@Repository
public class NewsDAO extends CcHibernateDao<News, Integer> {
	/**
	 * 查找指定id（审核状态）信息 2016-06-25 by p
	 * 
	 * @param id
	 * @param audit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public News findById(Integer id, Integer audit) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.ge("audit", audit));
		
		List<News> l = (List<News>) criteria.list();
		
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		else {
			return null;
		}
	}

	/**
	 * 查询所有信息  2016-06-25 by p
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findAll() {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查询所有信息数量  2016-06-25 by p
	 * 
	 * @return
	 */
	public int getCount() {
		Criteria criteria = this.getSession().createCriteria(News.class);
		
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
	public List<News> findAll(int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找分类（审核状态）信息 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(Integer mid, Integer audit) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找分类（审核状态）信息数量 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @return
	 */
	public int getCountByMid(Integer mid, Integer audit) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	/**
	 * 查找分类（审核状态）信息（分页） 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(Integer mid, Integer audit, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找分类（审核状态）信息,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找分类（审核状态）信息（数量）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @return
	 */
	public int getCountByMid(Integer mid, Integer audit, String title, String content, String author) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	/**
	 * 查找分类（审核状态）信息（分页）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(Integer mid, Integer audit, String title, String content, String author, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.eq("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找（审核状态）信息,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param text
	 * @param audit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findBySearch(String text, Integer audit) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.ge("audit", audit));
		
		if (text != null && text != "") {
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.ilike("title", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("content", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("author", text, MatchMode.ANYWHERE));
			
			criteria.add(disjunction);
			
			criteria.addOrder(Order.desc("istop"));
			criteria.addOrder(Order.desc("order"));
			criteria.addOrder(Order.desc("date"));
			criteria.addOrder(Order.desc("id"));
			
			return (List<News>) criteria.list();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * 查找（审核状态）信息（数量）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param text
	 * @param audit
	 * @return
	 */
	public int getCountBySearch(String text, Integer audit) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.ge("audit", audit));
		
		if (text != null && text != "") {
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.ilike("title", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("content", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("author", text, MatchMode.ANYWHERE));
			
			criteria.add(disjunction);
			
			return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * 查找（审核状态）信息（分页）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param text
	 * @param audit
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findBySearch(String text, Integer audit, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.ge("audit", audit));
		
		if (text != null && text != "") {
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.ilike("title", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("content", text, MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("author", text, MatchMode.ANYWHERE));
			
			criteria.add(disjunction);
			
			criteria.addOrder(Order.desc("istop"));
			criteria.addOrder(Order.desc("order"));
			criteria.addOrder(Order.desc("date"));
			criteria.addOrder(Order.desc("id"));
			
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(maxResult);
			
			return (List<News>) criteria.list();
		}
		else {
			return new ArrayList<>();
		}
	}
	
	/**
	 * 查找分类（列表）（审核状态）信息,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.in("menuId", mid));	
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		return (List<News>) criteria.list();
	}
	
	/**
	 * 查找分类（列表）（审核状态）信息（数量）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @return
	 */
	public int getCountByMid(List<Integer> mid, Integer audit, String title, String content, String author) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.in("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		return ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	/**
	 * 查找分类（列表）（审核状态）信息（分页）,并检索标题、内容、作者是否包含指定数据 2016-06-25 by p
	 * 
	 * @param mid
	 * @param audit
	 * @param title
	 * @param content
	 * @param author
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByMid(List<Integer> mid, Integer audit, String title, String content, String author, int firstResult, int maxResult) {
		Criteria criteria = getSession().createCriteria(News.class);
		
		criteria.add(Restrictions.in("menuId", mid));
		criteria.add(Restrictions.ge("audit", audit));
		
		if (title != null && title != "") {
			criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
		}
		
		if (content != null && content != "") {
			criteria.add(Restrictions.ilike("content", content, MatchMode.ANYWHERE));
		}
		
		if (author != null && author != "") {
			criteria.add(Restrictions.ilike("author", author, MatchMode.ANYWHERE));
		}
		
		criteria.addOrder(Order.desc("istop"));
		criteria.addOrder(Order.desc("order"));
		criteria.addOrder(Order.desc("date"));
		criteria.addOrder(Order.desc("id"));
		
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResult);
		
		return (List<News>) criteria.list();
	}
	
	public List<?> statistic(List<Integer> list) {
		
		Criteria criteria = getSession().createCriteria(News.class);
		criteria.add(Restrictions.in("NewsId", list));
		criteria.createAlias("user", "user");
		criteria.add(Restrictions.eq("user.state", 1));
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("user.id").as("userId"));
		projectionList.add(Projections.groupProperty("user.trueName").as("userName"));
		projectionList.add(Projections.count("user.id").as("num"));
		
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.desc("num"));
		return criteria.list();
	}
}
