package de.ifcore.metis.admin.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import de.ifcore.metis.admin.services.PixelPool;

public class PixelServerImplTest
{
	@Test
	public void assignShouldReturnNullWhenPoolIsEmpty()
	{
		PixelServerImpl pixelServer = new PixelServerImpl(null, mock(PixelPool.class));
		assertNull(pixelServer.assignPixel("abc", "def"));
	}
}
