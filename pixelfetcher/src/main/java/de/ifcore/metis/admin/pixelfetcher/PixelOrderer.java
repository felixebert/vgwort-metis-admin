package de.ifcore.metis.admin.pixelfetcher;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;

public class PixelOrderer
{
	private final PixelFetcher pixelFetcher;
	private final PixelStorage pixelStorage;

	private int poolSize = 10;

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
		if (reorderRequired(numberOfUnusedPixels))
		{
			pixelFetcher.fetch(poolSize);
		}
	}

	public boolean reorderRequired(int numberOfUnusedPixels)
	{
		int treshold = Math.round(poolSize / 10);
		return numberOfUnusedPixels <= treshold;
	}

	public int getPoolSize()
	{
		return poolSize;
	}

	public void setPoolSize(int poolSize)
	{
		this.poolSize = poolSize;
	}
}
