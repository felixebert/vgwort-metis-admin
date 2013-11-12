package de.ifcore.metis.admin.dao.hibernate;

import javax.inject.Inject;

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
}
