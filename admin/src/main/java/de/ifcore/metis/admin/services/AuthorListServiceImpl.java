package de.ifcore.metis.admin.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.AuthorDao;
import de.ifcore.metis.admin.entities.Author;

@Named
public class AuthorListServiceImpl implements AuthorListService
{
	private final AuthorDao authorDao;

	@Inject
	public AuthorListServiceImpl(AuthorDao authorDao)
	{
		this.authorDao = authorDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Author> getAuthors()
	{
		return authorDao.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<String> getUnknownAuthors()
	{
		return authorDao.getUnknownAuthorNames();
	}
}
