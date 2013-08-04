package de.ifcore.metis.admin.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SQLQuery;
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
		String sql = "SELECT p.* FROM pixel p LEFT JOIN pixelLink pl ON pl.publicPixelId = p.publicId WHERE pl.textId IS NULL";
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}
}
