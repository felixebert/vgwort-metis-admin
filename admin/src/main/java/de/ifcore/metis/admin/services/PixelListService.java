package de.ifcore.metis.admin.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.PixelLink;

@Named
public class PixelListService
{
	private final PixelLinkDao pixelLinkDao;

	@Inject
	public PixelListService(PixelLinkDao pixelLinkDao)
	{
		this.pixelLinkDao = pixelLinkDao;
	}

	@Transactional(readOnly = true)
	public Collection<PixelLink> getAllPixelLinks()
	{
		return pixelLinkDao.getAll();
	}
}
