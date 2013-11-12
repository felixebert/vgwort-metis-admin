package de.ifcore.metis.admin.services;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.models.PixelStatistic;

@Named
public class PixelStatisticServiceImpl implements PixelStatisticService
{
	private final PixelDao pixelDao;
	private final TextDao textDao;

	@Inject
	public PixelStatisticServiceImpl(PixelDao pixelDao, TextDao textDao)
	{
		this.pixelDao = pixelDao;
		this.textDao = textDao;
	}

	@Override
	@Transactional(readOnly = true)
	public PixelStatistic getStatistic()
	{
		long pixelCount = pixelDao.getCount();
		long textCount = textDao.getCount();
		return new PixelStatistic(pixelCount, textCount);
	}
}
