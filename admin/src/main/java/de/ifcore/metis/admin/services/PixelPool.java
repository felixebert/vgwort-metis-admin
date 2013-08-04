package de.ifcore.metis.admin.services;

public interface PixelPool
{
	/**
	 * @return size of the pixelPool (number of unlinked pixels)
	 */
	public int size();

	/**
	 * @return publicId of an unlinked pixel
	 */
	public String poll();
}