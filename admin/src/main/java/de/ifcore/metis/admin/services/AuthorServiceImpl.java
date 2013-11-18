package de.ifcore.metis.admin.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.AuthorDao;
import de.ifcore.metis.admin.entities.Author;
import de.ifcore.metis.admin.models.AuthorForm;

@Named
public class AuthorServiceImpl implements AuthorService
{
	private final AuthorDao authorDao;

	@Inject
	public AuthorServiceImpl(AuthorDao authorDao)
	{
		this.authorDao = authorDao;
	}

	@Override
	@Transactional(readOnly = true)
	public AuthorForm getAuthorForm(Long id)
	{
		return AuthorForm.copyOf(authorDao.get(id));
	}

	@Override
	@Transactional
	public void persist(AuthorForm form)
	{
		Author author = form.toAuthor();
		authorDao.save(author);
	}

	@Override
	@Transactional
	public void update(Long id, AuthorForm form)
	{
		Author author = authorDao.get(id);
		form.updateAuthor(author);
		authorDao.update(author);
	}

	@Override
	@Transactional
	public void delete(Long id)
	{
		Author author = authorDao.get(id);
		authorDao.delete(author);
	}
}
