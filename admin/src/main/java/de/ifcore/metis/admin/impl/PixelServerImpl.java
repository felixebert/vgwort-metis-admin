package de.ifcore.metis.admin.impl;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.services.PixelPool;
import de.ifcore.metis.pixelserver.PixelDigest;
import de.ifcore.metis.pixelserver.PixelServer;

@Named
public class PixelServerImpl implements PixelServer
{
	private static final Logger log = LoggerFactory.getLogger(PixelServerImpl.class);

	private final TextDao textDao;
	private final PixelPool pixelPool;

	private final Queue<String> urlQueue = new LinkedBlockingQueue<>(100);
	private final Set<String> urlBlacklist = new HashSet<>();

	@Inject
	public PixelServerImpl(TextDao textDao, PixelPool pixelPool)
	{
		this.textDao = textDao;
		this.pixelPool = pixelPool;
	}

	@Override
	@Transactional(readOnly = true)
	public PixelDigest getPixel(String url)
	{
		String textId = url;
		Text text = textDao.get(textId);

		if (text == null)
		{
			log.trace("no pixel assigned to textId " + textId);
			return null;
		}
		else
		{
			log.trace("returning assigned pixel for textId " + textId + " - " + text);
			Pixel pixelEntity = text.getPixel();
			return new PixelDigest(pixelEntity.getHost(), pixelEntity.getPublicId());
		}
	}

	@Override
	public void assignPixelTo(String url)
	{
		urlQueue.offer(url);
	}

	@Scheduled(fixedDelay = 30000)
	public void pollUrlQueue()
	{
		String url = urlQueue.poll();
		if (url != null)
		{
			String textId = url;
			Pixel pixel = pixelPool.poll();

			if (pixel == null)
			{
				log.trace("couldn't assign a pixel to textId " + textId);
			}
			else
			{
				log.trace("assigning a pixel to textId " + textId + " - " + pixel);
				Text text = new Text(textId, null, pixel);
				textDao.save(text);
			}
		}
	}
}
