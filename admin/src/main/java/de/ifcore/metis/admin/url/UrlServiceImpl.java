package de.ifcore.metis.admin.url;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.dao.TextUrlDao;
import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;

@Named
public class UrlServiceImpl implements UrlService
{
	private static final Logger log = LoggerFactory.getLogger(UrlServiceImpl.class);

	private final TextDao textDao;
	private final TextUrlDao textUrlDao;

	@Inject
	public UrlServiceImpl(TextDao textDao, TextUrlDao textUrlDao)
	{
		this.textDao = textDao;
		this.textUrlDao = textUrlDao;
	}

	@Override
	@Transactional
	public void register(GrabbedData data)
	{
		Text text = textDao.get(data.getId());
		if (text == null)
		{
			text = Text.forData(data);
			textDao.save(text);
			log.trace("registered new url and new text - " + data.getId() + " - " + data.getSource());
		}
		else
		{
			text.updateWith(data);
			TextUrl textUrl = new TextUrl(data.getSource(), text);
			text.addUrl(textUrl);
			textUrlDao.save(textUrl);
			textDao.update(text);
			log.trace("registered new url (on an existing text) - " + data.getSource());
		}
	}

	@Override
	@Transactional
	public void update(TextUrl url, GrabbedData data)
	{
		Text text = url.getText();
		if (!text.getId().equals(data.getId()))
		{
			delete(url);
			register(data);
			log.trace("transfered url " + data.getSource());
		}
		else
		{
			text.updateWith(data);
			textDao.update(text);
			log.trace("updated url " + data.getSource());
		}
	}

	@Override
	@Transactional
	public void delete(TextUrl url)
	{
		textUrlDao.delete(url);
		log.trace("deleted url " + url.getUrl());
	}
}
