package de.ifcore.metis.admin.pixelserver;

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

	public String getPublicPixelId(PixelServer pixelServer)
	{
		String publicPixelId = pixelServer.getPublicPixelId(textId);
		return (publicPixelId == null) ? pixelServer.assignPixel(textId, url) : publicPixelId;
	}
}
