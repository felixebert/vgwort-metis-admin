package de.ifcore.metis.admin.pixelfetcher;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.scheduling.annotation.Scheduled;

@Named
public class PixelOrderer
{
	private final PixelFetcher pixelFetcher;
	private final PixelStore pixelStore;

	@Inject
	public PixelOrderer(PixelFetcher pixelFetcher, PixelStore pixelStore)
	{
		this.pixelFetcher = pixelFetcher;
		this.pixelStore = pixelStore;
	}

	@Scheduled(fixedDelay = 10 * 60 * 1000)
	public void watch()
	{
		int numberOfUnusedPixels = pixelStore.getNumberOfUnusedPixels();
		if (numberOfUnusedPixels < 100)
		{
			pixelFetcher.fetch(1000);
		}
	}
}
