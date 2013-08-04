package de.ifcore.metis.admin.dao;

import java.io.Serializable;
import java.util.Collection;

public interface EntityCrDao<T, PK extends Serializable>
{
	public T get(PK id);

	public Collection<T> getAll();

	public T save(T object);

	public Class<T> getEntityClass();

	public long getCount();
}
