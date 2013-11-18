package de.ifcore.metis.admin.services;

import java.util.Collection;

import de.ifcore.metis.admin.entities.Author;

public interface AuthorListService
{
	public Collection<Author> getAuthors();

	public Collection<String> getUnknownAuthors();
}
