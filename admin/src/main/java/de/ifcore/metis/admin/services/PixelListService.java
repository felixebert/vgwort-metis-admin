package de.ifcore.metis.admin.services;

import java.util.Collection;

import de.ifcore.metis.admin.entities.PixelLink;

public interface PixelListService
{
	public Collection<PixelLink> getAllPixelLinks();
}