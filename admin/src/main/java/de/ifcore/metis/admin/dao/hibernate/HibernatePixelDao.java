package de.ifcore.metis.admin.dao.hibernate;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.entities.Pixel;

@Repository
public class HibernatePixelDao extends HibernateAbstractEntityDao<Pixel, String> implements PixelDao
{
	@Inject
	public HibernatePixelDao(SessionFactory sessionFactory)
	{
		super(Pixel.class, sessionFactory);
	}
}
