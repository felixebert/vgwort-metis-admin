package de.ifcore.metis.pixelserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PixelRequestTest
{
	@Test
	public void testPixelRequest()
	{
		String url = "http://www.mogelpower.de/cheats/GTA-Cheats-PC.html";
		PixelRequest pixelRequest = new PixelRequest(url);
		assertEquals(url, pixelRequest.getUrl());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPixelRequestLocalhost()
	{
		new PixelRequest("http://localhost/test.html");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPixelRequestValidation()
	{
		new PixelRequest("xx");
	}
}
