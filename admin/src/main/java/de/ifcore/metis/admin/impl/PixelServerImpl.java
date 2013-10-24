package de.ifcore.metis.admin.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.PixelLink;
import de.ifcore.metis.admin.services.PixelPool;
import de.ifcore.metis.pixelserver.PixelResponse;
import de.ifcore.metis.pixelserver.PixelServer;

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
	public PixelResponse getPixel(String textId)
	{
		PixelLink pixelLink = pixelLinkDao.get(textId);

		if (pixelLink == null)
		{
			log.trace("no pixel assigned to textId " + textId);
			return null;
		}
		else
		{
			log.trace("returning assigned pixel for textId " + textId + " - " + pixelLink);
			return pixelToPixelResponse(pixelLink.getPixel());
		}
	}

	@Override
	@Transactional
	public PixelResponse assignPixel(String textId, String url)
	{
		Pixel pixel = pixelPool.poll();

		if (pixel == null)
		{
			log.trace("couldn't assign a pixel to textId " + textId);
			return null;
		}
		else
		{
			log.trace("assigning a pixel to textId " + textId + " - " + pixel);
			PixelLink pixelLink = new PixelLink(textId, pixel, url);
			pixelLinkDao.save(pixelLink);
			return pixelToPixelResponse(pixelLink.getPixel());
		}
	}

	private PixelResponse pixelToPixelResponse(Pixel pixel)
	{
		return new PixelResponse(pixel.getHost(), pixel.getPublicId());
	}
}
