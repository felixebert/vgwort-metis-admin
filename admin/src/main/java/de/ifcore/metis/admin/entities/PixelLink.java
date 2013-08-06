package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

@Entity
public class PixelLink
{
	@Id
	@Column(nullable = false, updatable = false)
	private String textId;

	@OneToOne
	@JoinColumn(nullable = false, updatable = false, unique = true)
	private Pixel pixel;

	@Column(nullable = false, updatable = false, unique = true)
	private String url;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	PixelLink()
	{
	}

	public PixelLink(String textId, Pixel pixel, String url)
	{
		this.textId = textId;
		this.pixel = pixel;
		this.url = url;
		this.createdAt = new Date();
	}

	public String getTextId()
	{
		return textId;
	}

	public Pixel getPixel()
	{
		return pixel;
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
		return "PixelLink [textId=" + textId + ", pixel=" + pixel + ", url=" + url + "]";
	}
}
