package de.ifcore.metis.admin.dao;

import java.util.List;

import de.ifcore.metis.admin.entities.Author;

public interface AuthorDao extends EntityDao<Author, String>
{
	public List<String> getUnknownAuthorNames();
}
