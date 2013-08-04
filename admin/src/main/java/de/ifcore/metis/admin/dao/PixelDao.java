package de.ifcore.metis.admin.dao;

import java.util.List;

import de.ifcore.metis.admin.entities.Pixel;

public interface PixelDao extends EntityCrDao<Pixel, String>
{
	public List<Pixel> getUnlinkedPixels();
}
