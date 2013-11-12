package de.ifcore.metis.pixelserver;

import org.springframework.util.StringUtils;

public class RawPixelRequest
{
	private String category;
	private Long id;
	private String hostname;
	private String path;
	private String referer;

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

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
		if (StringUtils.isEmpty(category))
		{
			throw new IllegalArgumentException();
		}
		return new PixelRequest(category + "-" + id, getUrl());
	}

	@Override
	public String toString()
	{
		return "RawPixelRequest [category=" + category + ", id=" + id + ", hostname=" + hostname + ", path=" + path
				+ "]";
	}
}
