package de.ifcore.metis.pixelserver;

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
