package de.ifcore.metis.pixelfetcher;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Monitors the amount of unused pixels and automatically orders new pixels when the reserve runs short
 * 
 * @author felix
 * 
 */
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

	/**
	 * monitors the amount of unused pixels in the {@link PixelStorage}. reorders new pixels if necessary
	 */
	@Scheduled(fixedDelay = 10 * 60 * 1000)
	public void watch()
	{
		int numberOfUnusedPixels = pixelStorage.getNumberOfUnusedPixels();
		if (reorderRequired(numberOfUnusedPixels))
		{
			pixelFetcher.fetch(poolSize);
		}
	}

	/**
	 * @param numberOfUnusedPixels
	 * @return true when the number of unused pixels is less or equals to 10% of the poolSize
	 */
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
