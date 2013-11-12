package de.ifcore.metis.admin.dao.hibernate;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.ifcore.metis.admin.dao.TextUrlDao;
import de.ifcore.metis.admin.entities.TextUrl;

@Repository
public class HibernateTextUrlDao extends HibernateAbstractEntityDao<TextUrl, String> implements TextUrlDao
{
	@Inject
	public HibernateTextUrlDao(SessionFactory sessionFactory)
	{
		super(TextUrl.class, sessionFactory);
	}
}
