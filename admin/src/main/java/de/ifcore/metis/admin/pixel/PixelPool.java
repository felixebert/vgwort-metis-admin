package de.ifcore.metis.admin.pixel;

import de.ifcore.metis.admin.entities.Pixel;

public interface PixelPool
{
	/**
	 * @return size of the pixelPool (number of unlinked pixels)
	 */
	public int size();

	/**
	 * @return publicId of an unlinked pixel
	 */
	public Pixel poll();

	/**
	 * fill empty cache
	 */
	public void fillUp();
}