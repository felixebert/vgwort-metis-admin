package de.ifcore.metis.admin.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.PixelLink;
import de.ifcore.metis.admin.pixelserver.PixelServer;
import de.ifcore.metis.admin.services.PixelPool;

@Named
public class PixelServerImpl implements PixelServer
{
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
		return pixelLink != null ? pixelLink.getPublicPixelId() : null;
	}

	@Override
	@Transactional
	public String assignPixel(String textId, String url)
	{
		String publicPixelId = pixelPool.poll();
		if (publicPixelId != null)
		{
			pixelLinkDao.save(new PixelLink(textId, publicPixelId, url));
		}
		return publicPixelId;
	}
}
