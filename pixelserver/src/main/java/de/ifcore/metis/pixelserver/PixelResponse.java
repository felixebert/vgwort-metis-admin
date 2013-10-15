package de.ifcore.metis.pixelserver;

public class PixelResponse
{
	private final String host;
	private final String publicId;

	public PixelResponse(String host, String publicId)
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
		String src = "http://" + host + "/na/" + publicId;
		String imgTag = "<img src=\"" + src + "\" height=\"1\" width=\"1\" border=\"0\" />";
		return "document.write('" + imgTag + "');";
	}
}
