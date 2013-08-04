package de.ifcore.metis.admin.impl;

import javax.inject.Inject;
import javax.inject.Named;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.pixelserver.PixelServer;

@Named
public class PixelServerImpl implements PixelServer
{
	private final PixelLinkDao pixelLinkDao;

	@Inject
	public PixelServerImpl(PixelLinkDao pixelLinkDao)
	{
		this.pixelLinkDao = pixelLinkDao;
	}

	@Override
	public String getPublicPixelId(String textId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignPixel(String textId, String url)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
