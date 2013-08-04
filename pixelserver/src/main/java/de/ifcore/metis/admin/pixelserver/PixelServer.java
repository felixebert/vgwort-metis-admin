package de.ifcore.metis.admin.pixelserver;

public interface PixelServer
{
	public String getPublicPixelId(String textId);

	/**
	 * @param textId
	 * @param url
	 * @return publicPixelId
	 */
	public String assignPixel(String textId, String url);
}
