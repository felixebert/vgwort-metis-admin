package de.ifcore.metis.admin.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pixel
{
	@Id
	@Column(nullable = false, updatable = false)
	private String publicId;

	@Column(nullable = false, updatable = false)
	private String privateId;

	Pixel()
	{
	}

	public Pixel(String publicId, String privateId)
	{
		this.publicId = publicId;
		this.privateId = privateId;
	}

	public String getPublicId()
	{
		return publicId;
	}

	public String getPrivateId()
	{
		return privateId;
	}

	@Override
	public String toString()
	{
		return "Pixel [publicId=" + publicId + ", privateId=" + privateId + "]";
	}
}
