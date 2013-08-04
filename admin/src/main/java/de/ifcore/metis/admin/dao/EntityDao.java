package de.ifcore.metis.admin.dao;

import java.io.Serializable;

public interface EntityDao<T, PK extends Serializable> extends EntityCruDao<T, PK>
{
	public T delete(T object);
}
