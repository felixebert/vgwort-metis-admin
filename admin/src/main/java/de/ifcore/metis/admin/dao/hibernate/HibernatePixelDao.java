package de.ifcore.metis.admin.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Pixel> getUnlinkedPixels()
	{
		String hql = "SELECT p FROM Pixel p LEFT JOIN p.link pl WHERE pl.textId IS NULL";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
