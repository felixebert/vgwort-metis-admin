package de.ifcore.metis.admin.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;
import de.ifcore.metis.grabber.Grabber;

@Named
public class TextServiceImpl implements TextService
{
	private final TextDao textDao;
	private final Grabber grabber;

	@Inject
	public TextServiceImpl(TextDao textDao, Grabber grabber)
	{
		this.textDao = textDao;
		this.grabber = grabber;
	}

	@Override
	@Transactional(readOnly = true)
	public Text get(String id)
	{
		return textDao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getUrls(String id)
	{
		List<String> urls = new ArrayList<>();
		Text text = textDao.get(id);
		for (TextUrl textUrl : text.getUrls())
		{
			urls.add(textUrl.getUrl());
		}
		return urls;
	}

	@Override
	@Transactional(readOnly = true)
	public GrabbedData grab(String id)
	{
		Text text = textDao.get(id);
		if (!text.getUrls().isEmpty())
		{
			TextUrl textUrl = text.getUrls().iterator().next();
			return grabber.grab(textUrl.getUrl());
		}
		return null;
	}
}
