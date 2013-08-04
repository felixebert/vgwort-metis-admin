package de.ifcore.metis.admin.pixelfetcher;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;

public class PixelOrderer
{
	private final PixelFetcher pixelFetcher;
	private final PixelStorage pixelStorage;

	@Inject
	public PixelOrderer(PixelFetcher pixelFetcher, PixelStorage pixelStorage)
	{
		this.pixelFetcher = pixelFetcher;
		this.pixelStorage = pixelStorage;
	}

	@Scheduled(fixedDelay = 10 * 60 * 1000)
	public void watch()
	{
		int numberOfUnusedPixels = pixelStorage.getNumberOfUnusedPixels();
		if (numberOfUnusedPixels < 100)
		{
			pixelFetcher.fetch(1000);
		}
	}
}
