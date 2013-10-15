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
}
