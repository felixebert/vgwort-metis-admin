package de.ifcore.metis.admin.services;

import org.springframework.stereotype.Service;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.PixelLinkDao;

@Service
public class PixelPoolImpl implements PixelPool
{
	private final PixelDao pixelDao;
	private final PixelLinkDao pixelLinkDao;

	public PixelPoolImpl(PixelDao pixelDao, PixelLinkDao pixelLinkDao)
	{
		this.pixelDao = pixelDao;
		this.pixelLinkDao = pixelLinkDao;
	}

	@Override
	public int size()
	{
		long pixels = pixelDao.getCount();
		long linkedPixels = pixelLinkDao.getCount();
		return (int)(pixels - linkedPixels);
	}

	@Override
	public String poll()
	{
		return null;
	}
}
