package de.ifcore.metis.admin.dao;

import java.io.Serializable;

public interface EntityCruDao<T, PK extends Serializable> extends EntityCrDao<T, PK>
{
	public T update(T object);
}
