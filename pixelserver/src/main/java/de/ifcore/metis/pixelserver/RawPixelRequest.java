package de.ifcore.metis.pixelserver;

/**
 * Container for the raw request
 * 
 * @author felix
 * 
 */
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

	/**
	 * @return returns the referer if hostname or path is null, otherwise http:// + hostname + path
	 */
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

	/**
	 * Converts this raw, specific request holder to a generic {@link PixelRequest}
	 * 
	 * @return {@link PixelRequest}
	 */
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
