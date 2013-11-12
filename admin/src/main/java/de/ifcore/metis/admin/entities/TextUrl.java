package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity
public class TextUrl
{
	@Id
	@Column(nullable = false, updatable = false)
	private String url;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Text text;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	TextUrl()
	{
	}

	public TextUrl(String url, Text text, Date createdAt)
	{
		this.url = url;
		this.text = text;
		this.createdAt = new Date();
	}

	public String getUrl()
	{
		return url;
	}

	public Text getText()
	{
		return text;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	@Override
	public String toString()
	{
		return "TextUrl [url=" + url + ", createdAt=" + createdAt + "]";
	}
}
