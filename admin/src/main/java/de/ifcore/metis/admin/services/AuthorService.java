package de.ifcore.metis.admin.services;

import de.ifcore.metis.admin.models.AuthorForm;

public interface AuthorService
{
	public void persist(AuthorForm form);

	public void update(Long id, AuthorForm form);

	public AuthorForm getAuthorForm(Long id);

	public void delete(Long id);
}
