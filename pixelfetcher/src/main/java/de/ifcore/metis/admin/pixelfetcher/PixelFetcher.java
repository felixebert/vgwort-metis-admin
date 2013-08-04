package de.ifcore.metis.admin.pixelfetcher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import de.ifcore.metis.client.MetisClient;
import de.ifcore.metis.client.pixel.Pixel;

@Named
public class PixelFetcher
{
	private final MetisClient metisClient;
	private final PixelStorage pixelStorage;

	@Inject
	public PixelFetcher(MetisClient metisClient, PixelStorage pixelStorage)
	{
		this.metisClient = metisClient;
		this.pixelStorage = pixelStorage;
	}

	public void fetch(int count)
	{
		List<Pixel> pixels = metisClient.orderPixels(count);
		pixelStorage.addAll(pixels);
	}
}
