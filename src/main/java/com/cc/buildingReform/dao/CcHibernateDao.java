package com.cc.buildingReform.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * @author Zhang Kaitao
 * 
 * @version 1.0, 2010-8-12
 */
public abstract class CcHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(CcHibernateDao.class);

	private final Class<M> entityClass;
	private final String HQL_LIST_ALL;
	//private final String HQL_COUNT_ALL;
	private final String HQL_OPTIMIZE_PRE_LIST_ALL;
	private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
	private String pkName = null;

	@SuppressWarnings("unchecked")
	public CcHibernateDao() {
		this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Field[] fields = this.entityClass.getFields();
		for (Field f : fields) {
			System.out.println(f.getName());
			if (f.isAnnotationPresent(Id.class)) {
				this.pkName = f.getName();
			}
		}
		if (pkName == null)
			pkName = "id";
		HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
		HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName
				+ " > ? order by " + pkName + " asc";
		HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where "
				+ pkName + " < ? order by " + pkName + " desc";
		//HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
	}

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public PK save(M model) {
		return (PK) getSession().save(model);
	}

	public void saveOrUpdate(M model) {
		getSession().saveOrUpdate(model);
	}

	public void update(M model) {
		getSession().update(model);

	}

	public void merge(M model) {
		getSession().merge(model);
	}

	public void delete(PK id) {
		getSession().delete(this.get(id));

	}

	public void deleteObject(M model) {
		getSession().delete(model);

	}

	public boolean exists(PK id) {
		return get(id) != null;
	}

	@SuppressWarnings("unchecked")
	public M get(PK id) {
		return (M) getSession().get(this.entityClass, id);
	}

	public List<M> listAll() {
		return list(HQL_LIST_ALL, 0, 0);
	}

	public List<M> listAll(int position, int pageSize) {
		return list(HQL_LIST_ALL, position, pageSize);
	}

	public List<M> pre(PK pk, int position, int pageSize) {
		if (pk == null) {
			return list(HQL_LIST_ALL, position, pageSize);
		}
		List<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize, pk);
		Collections.reverse(result);
		return result;
	}

	public List<M> next(PK pk, int position, int pageSize) {
		if (pk == null) {
			return list(HQL_LIST_ALL, position, pageSize);
		}
		return list(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, pk);
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	protected long getIdResult(String hql, Object... paramlist) {
		long result = -1;
		List<?> list = list(hql, 0, 0, paramlist);
		if (list != null && list.size() > 0) {
			return ((Number) list.get(0)).longValue();
		}
		return result;
	}

	// protected List<M> listSelf(final String hql, final int position, final
	// int pageSize, final Object... paramlist) {
	// return this.<M> list(hql, position, pageSize, paramlist);
	// }

	/**
	 * for in
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> listWithIn(final String hql, final int start, final int length,
			final Map<String, Collection<?>> map, final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		for (Entry<String, Collection<?>> e : map.entrySet()) {
			query.setParameterList(e.getKey(), e.getValue());
		}
		if (start > -1 && length > -1) {
			query.setMaxResults(length);
			if (start != 0) {
				query.setFirstResult(start);
			}
		}
		List<T> results = query.list();
		return results;
	}

	public List<M> list(final String hql) {
		return list(hql, 0, 0);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> listSql(final String hql) {
		return getSession().createSQLQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	public List<M> list(final String hql, final int position, final int pageSize,
			final Object... paramlist) {
		Query query = getSession().createQuery(hql);
		setParameters(query, paramlist);
		if (pageSize > 0) {
			query.setFirstResult(position);
			query.setMaxResults(pageSize);
		}
		List<M> results = query.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	protected <T> T aggregateByNative(final String natvieSQL,
			final List<Entry<String, Type>> scalarList, final Object... paramlist) {
		SQLQuery query = getSession().createSQLQuery(natvieSQL);
		if (scalarList != null) {
			for (Entry<String, Type> entity : scalarList) {
				query.addScalar(entity.getKey(), entity.getValue());
			}
		}

		setParameters(query, paramlist);

		Object result = query.uniqueResult();
		return (T) result;
	}

	@SuppressWarnings("unchecked")
	public <T> T unique(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T> T unique(DetachedCriteria criteria) {
		return (T) unique(criteria.getExecutableCriteria(getSession()));
	}

	protected static void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					query.setTimestamp(i, (Date) paramlist[i]);
				} else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

}
