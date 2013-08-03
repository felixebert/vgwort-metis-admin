package de.ifcore.metis.admin.pixelserver;

public interface PixelServer
{
	public String getPublicPixelId(String pageId);

	/**
	 * @param pageId
	 * @param url
	 * @return publicPixelId
	 */
	public String assignPixel(String pageId, String url);
}
