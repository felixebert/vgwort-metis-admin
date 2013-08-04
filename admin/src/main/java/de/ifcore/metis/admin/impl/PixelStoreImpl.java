package de.ifcore.metis.admin.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.pixelfetcher.PixelStore;

@Named
public class PixelStoreImpl implements PixelStore
{
	private final PixelDao pixelDao;

	@Inject
	public PixelStoreImpl(PixelDao pixelDao)
	{
		this.pixelDao = pixelDao;
	}

	@Override
	public int getNumberOfUnusedPixels()
	{
		return 0;
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
}
