package de.ifcore.metis.admin.pixelserver;

import org.apache.commons.validator.routines.UrlValidator;

public class Page
{
	private final String id;
	private final String url;

	public Page(String id, String url)
	{
		if (id == null || id.isEmpty() || !UrlValidator.getInstance().isValid(url))
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.url = url;
	}

	public String getId()
	{
		return id;
	}

	public String getUrl()
	{
		return url;
	}

	@Override
	public String toString()
	{
		return "Page [id=" + id + ", url=" + url + "]";
	}

	public String getPublicPixelId(PixelServer pixelServer)
	{
		String publicPixelId = pixelServer.getPublicPixelId(id);
		return (publicPixelId == null) ? pixelServer.assignPixel(id, url) : publicPixelId;
	}
}
