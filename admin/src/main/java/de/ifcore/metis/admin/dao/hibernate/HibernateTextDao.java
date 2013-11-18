package de.ifcore.metis.admin.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Text;

@Repository
public class HibernateTextDao extends HibernateAbstractEntityDao<Text, String> implements TextDao
{
	@Inject
	public HibernateTextDao(SessionFactory sessionFactory)
	{
		super(Text.class, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Text> getTextsWithoutPixel()
	{
		String hql = "FROM Text WHERE pixel IS NULL";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
