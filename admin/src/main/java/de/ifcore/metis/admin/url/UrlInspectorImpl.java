package de.ifcore.metis.admin.url;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextUrlDao;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;
import de.ifcore.metis.grabber.Grabber;

@Named
public class UrlInspectorImpl implements UrlInspector
{
	private static final Logger log = LoggerFactory.getLogger(UrlInspectorImpl.class);

	private final Grabber grabber;
	private final TextUrlDao textUrlDao;
	private final UrlService urlService;

	@Inject
	public UrlInspectorImpl(Grabber grabber, TextUrlDao textUrlDao, UrlService urlService)
	{
		this.grabber = grabber;
		this.textUrlDao = textUrlDao;
		this.urlService = urlService;
	}

	@Override
	public void inspect(String url)
	{
		GrabbedData data = grabber.grab(url);
		inspect(data);
	}

	@Override
	@Transactional(readOnly = true)
	public void inspect(GrabbedData data)
	{
		TextUrl textUrl = textUrlDao.get(data.getSource());
		if (textUrl == null)
		{
			inspectUnknownUrl(data);
		}
		else
		{
			inspectKnownUrl(textUrl, data);
		}
	}

	@Override
	public void inspectUnknownUrl(GrabbedData data)
	{
		if (data.getId() != null)
		{
			urlService.register(data);
		}
		else
		{
			log.warn("couldn't register " + data.getSource() + " - no vgwort id found");
		}
	}

	@Override
	public void inspectKnownUrl(TextUrl url, GrabbedData data)
	{
		if (data.getId() == null)
		{
			urlService.delete(url);
		}
		else
		{
			urlService.update(url, data);
		}
	}
}
