package de.ifcore.metis.admin.pixelserver;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

public class RawPixelRequest
{
	private String category;
	private Long id;
	private String url;

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

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDecodedUrl()
	{
		return new String(Base64.decodeBase64(url));
	}

	public PixelRequest process()
	{
		if (StringUtils.isEmpty(category))
		{
			throw new IllegalArgumentException();
		}
		return new PixelRequest(category + "-" + id, getDecodedUrl());
	}

	@Override
	public String toString()
	{
		return "RawPixelRequest [category=" + category + ", id=" + id + "]";
	}
}
