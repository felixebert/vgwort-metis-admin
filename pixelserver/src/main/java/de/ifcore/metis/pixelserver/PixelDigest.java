package de.ifcore.metis.pixelserver;

/**
 * This class holds all necessary pixel fields to use a VG-WORT pixel inside a webpage (using an img tag)
 * 
 * @author felix
 * 
 */
public class PixelDigest
{
	private final String host;
	private final String publicId;

	public PixelDigest(String host, String publicId)
	{
		if (host == null || publicId == null)
		{
			throw new IllegalArgumentException();
		}

		this.host = host;
		this.publicId = publicId;
	}

	public String getHost()
	{
		return host;
	}

	public String getPublicId()
	{
		return publicId;
	}

	@Override
	public String toString()
	{
		return "PixelDigest [host=" + host + ", publicId=" + publicId + "]";
	}
}
