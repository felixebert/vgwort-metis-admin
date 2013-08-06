package de.ifcore.metis.admin.utils;

import org.apache.commons.lang3.RandomStringUtils;

import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.PixelLink;

public class EntityTestUtils
{
	public static Pixel mockPixel()
	{
		return mockPixel(rnd(10));
	}

	public static Pixel mockPixel(String publicPixelId)
	{
		return new Pixel(publicPixelId, rnd(10), rnd(10));
	}

	public static PixelLink mockPixelLink()
	{
		return mockPixelLink(mockPixel());
	}

	public static PixelLink mockPixelLink(Pixel pixel)
	{
		return new PixelLink(rnd(10), pixel, rnd(10));
	}

	private static String rnd(int count)
	{
		return RandomStringUtils.randomAlphabetic(count);
	}
}
