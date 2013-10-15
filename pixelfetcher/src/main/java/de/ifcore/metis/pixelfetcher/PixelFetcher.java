package de.ifcore.metis.pixelfetcher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ifcore.metis.client.MetisClient;
import de.ifcore.metis.client.pixel.Pixel;

@Named
public class PixelFetcher
{
	private static final Logger log = LoggerFactory.getLogger(PixelFetcher.class);

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
		log.debug("fetched " + pixels.size() + " pixels");
		pixelStorage.addAll(pixels);
	}
}
