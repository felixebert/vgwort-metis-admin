package de.ifcore.metis.pixelserver;

/**
 * Builds an img tag pointing to the VG-Wort Pixel Image and wraps it inside the JavaScript function document.write
 * 
 * @author felix
 * 
 */
public class PixelResponse
{
	private final PixelDigest pixel;
	private final String responseString;

	public PixelResponse(PixelDigest pixel)
	{
		this.pixel = pixel;
		this.responseString = buildResponseString();
	}

	public PixelDigest getPixel()
	{
		return pixel;
	}

	private String buildResponseString()
	{
		if (pixel == null)
		{
			return "";
		}

		String src = "http://" + pixel.getHost() + "/na/" + pixel.getPublicId();
		String imgTag = "<img src=\"" + src + "\" height=\"1\" width=\"1\" border=\"0\" />";
		return "document.write('" + imgTag + "');";
	}

	@Override
	public String toString()
	{
		return responseString;
	}
}
