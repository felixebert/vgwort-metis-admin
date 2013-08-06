package de.ifcore.metis.admin.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.PixelLink;
import de.ifcore.metis.admin.pixelserver.PixelServer;
import de.ifcore.metis.admin.services.PixelPool;

@Named
public class PixelServerImpl implements PixelServer
{
	private static final Logger log = LoggerFactory.getLogger(PixelServerImpl.class);

	private final PixelLinkDao pixelLinkDao;
	private final PixelPool pixelPool;

	@Inject
	public PixelServerImpl(PixelLinkDao pixelLinkDao, PixelPool pixelPool)
	{
		this.pixelLinkDao = pixelLinkDao;
		this.pixelPool = pixelPool;
	}

	@Override
	@Transactional(readOnly = true)
	public String getPublicPixelId(String textId)
	{
		PixelLink pixelLink = pixelLinkDao.get(textId);
		log.trace("pixelLink for textId " + textId + ": " + pixelLink);
		return pixelLink != null ? pixelLink.getPixel().getPublicId() : null;
	}

	@Override
	@Transactional
	public String assignPixel(String textId, String url)
	{
		log.trace("assignPixel for textId " + textId + " and url " + url);
		Pixel pixel = pixelPool.poll();

		if (pixel == null)
		{
			return null;
		}

		log.trace("assigning pixel " + pixel);
		PixelLink pixelLink = new PixelLink(textId, pixel, url);
		pixelLinkDao.save(pixelLink);
		return pixel.getPublicId();
	}
}
