package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.joda.time.DateTime;

@Entity
public class PixelLink
{
	@Id
	@Column(nullable = false, updatable = false)
	private String textId;

	@Column(nullable = false, updatable = false, unique = true)
	private String publicPixelId;

	@Column(nullable = false, updatable = false, unique = true)
	private String url;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	PixelLink()
	{
	}

	public PixelLink(String textId, String publicPixelId, String url)
	{
		this.textId = textId;
		this.publicPixelId = publicPixelId;
		this.url = url;
		this.createdAt = new Date();
	}

	public String getTextId()
	{
		return textId;
	}

	public String getPublicPixelId()
	{
		return publicPixelId;
	}

	public String getUrl()
	{
		return url;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	@Override
	public String toString()
	{
		return "PixelLink [textId=" + textId + ", publicPixelId=" + publicPixelId + ", url=" + url + "]";
	}
}
