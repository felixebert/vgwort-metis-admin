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
	private final PixelStore pixelStore;

	@Inject
	public PixelFetcher(MetisClient metisClient, PixelStore pixelStore)
	{
		this.metisClient = metisClient;
		this.pixelStore = pixelStore;
	}

	public void fetch(int count)
	{
		List<Pixel> pixels = metisClient.orderPixels(count);
		pixelStore.addAll(pixels);
	}
}
