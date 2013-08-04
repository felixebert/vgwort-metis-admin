package de.ifcore.metis.admin.dao.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import de.ifcore.metis.admin.dao.EntityDao;

public abstract class HibernateAbstractEntityDao<T, PK extends Serializable> extends HibernateAbstractDao implements
		EntityDao<T, PK>
{
	private final Class<T> type;

	public HibernateAbstractEntityDao(Class<T> type, SessionFactory sessionFactory)
	{
		super(sessionFactory);
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(PK id)
	{
		return id == null ? null : (T)getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> getAll()
	{
		Query query = getSession().createQuery("from " + type.getName());
		query.setCacheable(true);
		return Collections.unmodifiableCollection(query.list());
	}

	@Override
	public T save(T object)
	{
		getSession().persist(object);
		return object;
	}

	@Override
	public T update(T object)
	{
		getSession().merge(object);
		return object;
	}

	@Override
	public T delete(T object)
	{
		getSession().delete(object);
		return object;
	}

	@Override
	public Class<T> getEntityClass()
	{
		return type;
	}
}
