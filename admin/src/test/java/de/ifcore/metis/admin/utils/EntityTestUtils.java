package de.ifcore.metis.admin.utils;

import org.apache.commons.lang3.RandomStringUtils;

import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;

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

	public static Text mockText()
	{
		return mockText(mockPixel());
	}

	public static Text mockText(Pixel pixel)
	{
		return new Text(rnd(10), rnd(10), pixel);
	}

	private static String rnd(int count)
	{
		return RandomStringUtils.randomAlphabetic(count);
	}
}
