package de.ifcore.metis.pixelfetcher;

import java.util.List;

import de.ifcore.metis.client.pixel.Pixel;

/**
 * Generic PixelStorage interface
 * 
 * @author felix
 * 
 */
public interface PixelStorage
{
	/**
	 * @return number of unused (unassociated / unlinked) pixels
	 */
	public int getNumberOfUnusedPixels();

	/**
	 * persists all given pixels
	 * 
	 * @param pixels
	 */
	public void addAll(List<Pixel> pixels);
}
