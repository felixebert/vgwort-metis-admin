package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.joda.time.DateTime;

@Entity
public class Pixel
{
	@Id
	@Column(nullable = false, updatable = false)
	private String publicId;

	@Column(nullable = false, updatable = false)
	private String privateId;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	Pixel()
	{
	}

	public Pixel(String publicId, String privateId)
	{
		this.publicId = publicId;
		this.privateId = privateId;
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

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	@Override
	public String toString()
	{
		return "Pixel [publicId=" + publicId + ", privateId=" + privateId + "]";
	}
}
