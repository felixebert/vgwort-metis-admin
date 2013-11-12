package de.ifcore.metis.admin.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Text;

@Named
public class TextListServiceImpl implements TextListService
{
	private final TextDao textDao;

	@Inject
	public TextListServiceImpl(TextDao textDao)
	{
		this.textDao = textDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Text> getTexts()
	{
		return textDao.getAll();
	}
}
