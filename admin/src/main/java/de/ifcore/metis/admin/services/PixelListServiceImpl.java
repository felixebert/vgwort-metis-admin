package de.ifcore.metis.admin.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.PixelLink;

@Named
public class PixelListServiceImpl implements PixelListService
{
	private final PixelLinkDao pixelLinkDao;

	@Inject
	public PixelListServiceImpl(PixelLinkDao pixelLinkDao)
	{
		this.pixelLinkDao = pixelLinkDao;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PixelLink> getAllPixelLinks()
	{
		return pixelLinkDao.getAll();
	}
}
