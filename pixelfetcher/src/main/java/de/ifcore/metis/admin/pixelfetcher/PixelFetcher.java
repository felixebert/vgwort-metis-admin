package de.ifcore.metis.admin.pixelfetcher;

import javax.inject.Inject;

import de.ifcore.metis.client.MetisClient;

public class PixelFetcher
{
	private final MetisClient metisClient;

	@Inject
	public PixelFetcher(MetisClient metisClient)
	{
		this.metisClient = metisClient;
	}

	public void fetch()
	{
		metisClient.orderPixels(10);
	}
}
