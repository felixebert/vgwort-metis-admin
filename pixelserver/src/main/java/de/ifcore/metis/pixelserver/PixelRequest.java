package de.ifcore.metis.pixelserver;

import org.apache.commons.validator.routines.UrlValidator;

public class PixelRequest
{
	private final String url;

	public PixelRequest(String url)
	{
		if (!UrlValidator.getInstance().isValid(url))
		{
			throw new IllegalArgumentException();
		}
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	@Override
	public String toString()
	{
		return "PixelRequest [url=" + url + "]";
	}

	public PixelResponse getResponse(PixelServer pixelServer)
	{
		PixelDigest pixel = pixelServer.getPixel(url);
		if (pixel == null)
		{
			pixelServer.assignPixelTo(url);
		}
		return new PixelResponse(pixel);
	}
}
