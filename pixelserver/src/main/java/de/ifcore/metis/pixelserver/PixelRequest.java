package de.ifcore.metis.pixelserver;

import org.apache.commons.validator.routines.UrlValidator;

public class PixelRequest
{
	private final String textId;
	private final String url;

	public PixelRequest(String textId, String url)
	{
		if (textId == null || textId.isEmpty() || !UrlValidator.getInstance().isValid(url))
		{
			throw new IllegalArgumentException();
		}
		this.textId = textId;
		this.url = url;
	}

	public String getTextId()
	{
		return textId;
	}

	public String getUrl()
	{
		return url;
	}

	@Override
	public String toString()
	{
		return "Text [textId=" + textId + ", url=" + url + "]";
	}

	public PixelResponse getResponse(PixelServer pixelServer)
	{
		PixelResponse response = pixelServer.getPixel(textId);
		return (response == null) ? pixelServer.assignPixel(textId, url) : response;
	}
}
