package de.ifcore.metis.admin.services;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.Pixel;

@Service
public class PixelPoolImpl implements PixelPool
{
	private final PixelDao pixelDao;
	private final PixelLinkDao pixelLinkDao;

	private final Queue<String> pool = new ConcurrentLinkedQueue<>();

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

	@Scheduled(fixedDelay = 60 * 1000)
	@Transactional(readOnly = true)
	public void fillUp()
	{
		if (pool.isEmpty())
		{
			List<Pixel> unlinkedPixels = pixelDao.getUnlinkedPixels();
			for (Pixel pixel : unlinkedPixels)
			{
				pool.add(pixel.getPublicId());
			}
		}
	}

	@Override
	public String poll()
	{
		return pool.poll();
	}
}
