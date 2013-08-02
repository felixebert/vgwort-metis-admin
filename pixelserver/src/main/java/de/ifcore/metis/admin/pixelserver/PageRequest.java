package de.ifcore.metis.admin.pixelserver;

import org.springframework.util.StringUtils;

public class PageRequest
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

	public Page toPage()
	{
		if (StringUtils.isEmpty(category))
		{
			throw new IllegalArgumentException();
		}
		return new Page(category + "-" + id, url);
	}

	@Override
	public String toString()
	{
		return "PageRequest [category=" + category + ", id=" + id + ", url=" + url + "]";
	}
}
