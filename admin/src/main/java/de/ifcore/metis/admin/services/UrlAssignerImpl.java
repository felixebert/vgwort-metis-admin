package de.ifcore.metis.admin.services;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;
import de.ifcore.metis.grabber.Grabber;

@Named
public class UrlAssignerImpl implements UrlAssigner
{
	private static final Logger log = LoggerFactory.getLogger(UrlAssignerImpl.class);

	private final PixelPool pixelPool;
	private final Grabber grabber;
	private final TextDao textDao;

	private final Queue<String> urlQueue = new LinkedBlockingQueue<>(100);
	private final Set<String> urlBlacklist = new HashSet<>();

	@Inject
	public UrlAssignerImpl(PixelPool pixelPool, Grabber grabber, TextDao textDao)
	{
		this.pixelPool = pixelPool;
		this.grabber = grabber;
		this.textDao = textDao;
	}

	@Override
	public void offer(String url)
	{
		if (!urlBlacklist.contains(url))
		{
			urlQueue.offer(url);
		}
	}

	@Override
	public void execute()
	{
		String url = urlQueue.poll();
		if (url != null)
		{
			GrabbedData data = grabber.grab(url);

			if (data.getId() != null)
			{
				Text text = textDao.get(data.getId());
				if (text == null)
				{
					// TODO fix authorlist
					String author = data.getAuthors().isEmpty() ? null : data.getAuthors().get(0);
					Pixel pixel = pixelPool.poll();
					if (pixel == null)
					{
						log.trace("couldn't assign a pixel to text " + data.getId());
					}
					else
					{
						log.trace("assigning a pixel to text " + data.getId());
						text = new Text(data.getId(), author, pixel);
						text.addUrl(new TextUrl(url, text));
						textDao.save(text);
					}
				}
				else
				{
					text.addUrl(new TextUrl(url, text));
					textDao.update(text);
				}
			}
		}
	}
}
