package de.ifcore.metis.pixelfetcher;

import java.util.List;

import de.ifcore.metis.client.pixel.Pixel;

public interface PixelStorage
{
	public int getNumberOfUnusedPixels();

	public void addAll(List<Pixel> pixels);
}
