package de.ifcore.metis.pixelserver;

public interface PixelServer
{
	/**
	 * @param url
	 * @return the associated pixel or null
	 */
	public PixelDigest getPixel(String url);

	public void assignPixelTo(String url);
}
