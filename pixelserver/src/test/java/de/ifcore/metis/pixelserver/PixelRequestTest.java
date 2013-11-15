package de.ifcore.metis.pixelserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PixelRequestTest
{
	@Test
	public void testPixelRequest()
	{
		String url = "http://mogelpower.de/cheats/GTA-Cheats-PC.html";
		PixelRequest pixelRequest = new PixelRequest(url);
		assertEquals(url, pixelRequest.getUrl());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPixelRequestValidation()
	{
		new PixelRequest("http://localhost/test.html");
	}
}
