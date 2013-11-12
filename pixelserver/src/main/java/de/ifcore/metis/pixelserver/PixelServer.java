package de.ifcore.metis.pixelserver;

public interface PixelServer
{
	public PixelDigest getPixel(String url);

	public void assignPixelTo(String url);
}
