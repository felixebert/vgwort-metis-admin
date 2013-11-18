package de.ifcore.metis.admin.pixel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.pixelfetcher.PixelStorage;

@Named
public class PixelStorageImpl implements PixelStorage
{
	private static final Logger log = LoggerFactory.getLogger(PixelStorageImpl.class);

	private final PixelDao pixelDao;
	private final TextDao textDao;

	@Inject
	public PixelStorageImpl(PixelDao pixelDao, TextDao textDao)
	{
		this.pixelDao = pixelDao;
		this.textDao = textDao;
	}

	@Override
	@Transactional
	public void addAll(List<de.ifcore.metis.client.pixel.Pixel> pixels)
	{
		for (de.ifcore.metis.client.pixel.Pixel pixel : pixels)
		{
			Pixel pixelEntity = new Pixel(pixel.getPublicId(), pixel.getPrivateId(), pixel.getHost());
			pixelDao.save(pixelEntity);
		}

		log.debug("persisted " + pixels.size() + " pixels");
	}

	@Override
	@Transactional(readOnly = true)
	public int getNumberOfUnusedPixels()
	{
		long pixels = pixelDao.getCount();
		long linkedPixels = textDao.getCount();
		return (int)(pixels - linkedPixels);
	}
}
