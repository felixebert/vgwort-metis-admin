package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pixel
{
	@Id
	@Column(nullable = false, updatable = false)
	private String publicId;

	@Column(nullable = false, updatable = false)
	private String privateId;

	@Column(nullable = false, updatable = false)
	private String host;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@OneToOne(mappedBy = "pixel")
	@PrimaryKeyJoinColumn
	@JsonIgnore
	private Text text;

	Pixel()
	{
	}

	public Pixel(String publicId, String privateId, String host)
	{
		this.publicId = publicId;
		this.privateId = privateId;
		this.host = host;
		this.createdAt = new Date();
	}

	public String getPublicId()
	{
		return publicId;
	}

	public String getPrivateId()
	{
		return privateId;
	}

	public String getHost()
	{
		return host;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	public Text getText()
	{
		return text;
	}

	@Override
	public String toString()
	{
		return "Pixel [publicId=" + publicId + ", privateId=" + privateId + "]";
	}
}
