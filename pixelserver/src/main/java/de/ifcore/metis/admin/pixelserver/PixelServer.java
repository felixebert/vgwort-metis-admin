package de.ifcore.metis.admin.pixelserver;

public interface PixelServer
{
	public String getPublicPixelId(String pageId);

	public String assignPixel(String pageId, String url);
}
