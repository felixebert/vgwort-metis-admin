package de.ifcore.metis.admin.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.ifcore.metis.admin.dao.AuthorDao;
import de.ifcore.metis.admin.entities.Author;

@Repository
public class HibernateAuthorDao extends HibernateAbstractEntityDao<Author, String> implements AuthorDao
{
	@Inject
	public HibernateAuthorDao(SessionFactory sessionFactory)
	{
		super(Author.class, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUnknownAuthorNames()
	{
		String hql = "SELECT t.author FROM Text t WHERE NOT EXISTS (FROM AuthorAlias aa WHERE aa.name = t.author)";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
