package de.ifcore.metis.pixelserver;

public interface PixelServer
{
	public PixelResponse getPixel(String url);

	public void assignPixelTo(String url);
}
