package de.ifcore.metis.admin.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.models.PixelStatistic;

@Named
public class PixelStatisticService
{
	private final PixelDao pixelDao;
	private final PixelLinkDao pixelLinkDao;

	@Inject
	public PixelStatisticService(PixelDao pixelDao, PixelLinkDao pixelLinkDao)
	{
		this.pixelDao = pixelDao;
		this.pixelLinkDao = pixelLinkDao;
	}

	@Transactional(readOnly = true)
	public PixelStatistic getStatistic()
	{
		long pixelCount = pixelDao.getCount();
		long pixelLinkCount = pixelLinkDao.getCount();
		return new PixelStatistic(pixelCount, pixelLinkCount);
	}
}
