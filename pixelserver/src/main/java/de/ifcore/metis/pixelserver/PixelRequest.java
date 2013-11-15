package de.ifcore.metis.pixelserver;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Generic pixel request
 * 
 * @author felix
 */
public class PixelRequest
{
	private final String url;

	public PixelRequest(String url)
	{
		if (!UrlValidator.getInstance().isValid(url))
		{
			throw new IllegalArgumentException(url);
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

	/**
	 * Retrieves the associated pixel and generates a response. Requests the assignment of a pixel to the url if there
	 * isn't an associated pixel.
	 * 
	 * @param pixelServer
	 * @return {@link PixelResponse}, never null
	 */
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
