package de.ifcore.metis.pixelserver;

public class RawPixelRequest
{
	private String hostname;
	private String path;
	private String referer;

	public String getHostname()
	{
		return hostname;
	}

	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getReferer()
	{
		return referer;
	}

	public void setReferer(String referer)
	{
		this.referer = referer;
	}

	public String getUrl()
	{
		if (hostname == null || path == null)
		{
			return referer;
		}
		else
		{
			return "http://" + hostname + path;
		}
	}

	public PixelRequest process()
	{
		return new PixelRequest(getUrl());
	}

	@Override
	public String toString()
	{
		return "RawPixelRequest [hostname=" + hostname + ", path=" + path + ", referer=" + referer + "]";
	}
}
