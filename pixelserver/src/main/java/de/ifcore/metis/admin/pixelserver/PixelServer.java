package de.ifcore.metis.admin.pixelserver;

public interface PixelServer
{
	public PixelResponse getPixel(String textId);

	public PixelResponse assignPixel(String textId, String url);
}
