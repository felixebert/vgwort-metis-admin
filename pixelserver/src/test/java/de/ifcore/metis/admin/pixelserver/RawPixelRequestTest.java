package de.ifcore.metis.admin.pixelserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.ifcore.metis.pixelserver.RawPixelRequest;

public class RawPixelRequestTest
{
	@Test
	public void testGetUrl()
	{
		RawPixelRequest request = new RawPixelRequest();
		request.setHostname("www.mogelpower.de");
		request.setPath("/cheats/Alcatraz--In_PC_35480.html");

		assertEquals("http://www.mogelpower.de/cheats/Alcatraz--In_PC_35480.html", request.getUrl());
	}

	@Test
	public void testGetUrlWithReferer()
	{
		RawPixelRequest request = new RawPixelRequest();
		request.setHostname("www.mogelpower.de");
		request.setPath("/cheats/Alcatraz--In_PC_35480.html");
		request.setReferer("http://www.mogelpower.de");

		assertEquals("http://www.mogelpower.de/cheats/Alcatraz--In_PC_35480.html", request.getUrl());
	}

	@Test
	public void testGetUrlByReferer()
	{
		RawPixelRequest request = new RawPixelRequest();
		request.setReferer("http://www.mogelpower.de/cheats/Alcatraz--In_PC_35480.html");

		assertEquals(request.getReferer(), request.getUrl());
	}
}
