package de.ifcore.metis.admin.pixelfetcher;

import java.util.List;

import de.ifcore.metis.client.pixel.Pixel;

public interface PixelStore
{
	public int getNumberOfUnusedPixels();

	public void addAll(List<Pixel> pixels);
}
