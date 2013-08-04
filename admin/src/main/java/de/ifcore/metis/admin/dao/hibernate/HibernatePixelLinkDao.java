package de.ifcore.metis.admin.dao.hibernate;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.PixelLink;

@Repository
public class HibernatePixelLinkDao extends HibernateAbstractEntityDao<PixelLink, String> implements PixelLinkDao
{
	@Inject
	public HibernatePixelLinkDao(SessionFactory sessionFactory)
	{
		super(PixelLink.class, sessionFactory);
	}
}
