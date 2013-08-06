package de.ifcore.metis.admin.services;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.Pixel;

@Named
public class PixelPoolImpl implements PixelPool
{
	private static final Logger log = LoggerFactory.getLogger(PixelPoolImpl.class);

	private final PixelDao pixelDao;
	private final PixelLinkDao pixelLinkDao;

	private final Queue<Pixel> pool = new ConcurrentLinkedQueue<>();

	@Inject
	public PixelPoolImpl(PixelDao pixelDao, PixelLinkDao pixelLinkDao)
	{
		this.pixelDao = pixelDao;
		this.pixelLinkDao = pixelLinkDao;
	}

	@Override
	@Transactional(readOnly = true)
	public int size()
	{
		long pixels = pixelDao.getCount();
		long linkedPixels = pixelLinkDao.getCount();
		return (int)(pixels - linkedPixels);
	}

	@Override
	@Scheduled(fixedDelay = 60 * 1000)
	@Transactional(readOnly = true)
	public void fillUp()
	{
		if (pool.isEmpty())
		{
			List<Pixel> unlinkedPixels = pixelDao.getUnlinkedPixels();
			for (Pixel pixel : unlinkedPixels)
			{
				pool.add(pixel);
			}

			log.debug("pixelPool refilled");
		}
	}

	@Override
	public Pixel poll()
	{
		return pool.poll();
	}
}
