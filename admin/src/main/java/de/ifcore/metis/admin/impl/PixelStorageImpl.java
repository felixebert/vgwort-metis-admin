package de.ifcore.metis.admin.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.pixelfetcher.PixelStorage;
import de.ifcore.metis.admin.services.PixelPool;

@Named
public class PixelStorageImpl implements PixelStorage
{
	private final PixelDao pixelDao;
	private final PixelPool pixelPool;

	@Inject
	public PixelStorageImpl(PixelDao pixelDao, PixelPool pixelPool)
	{
		this.pixelDao = pixelDao;
		this.pixelPool = pixelPool;
	}

	@Override
	@Transactional
	public void addAll(List<de.ifcore.metis.client.pixel.Pixel> pixels)
	{
		for (de.ifcore.metis.client.pixel.Pixel pixel : pixels)
		{
			Pixel pixelEntity = new Pixel(pixel.getPublicIdentificationId(), pixel.getPrivateIdentificationId());
			pixelDao.save(pixelEntity);
		}
	}

	@Override
	public int getNumberOfUnusedPixels()
	{
		return pixelPool.size();
	}
}
