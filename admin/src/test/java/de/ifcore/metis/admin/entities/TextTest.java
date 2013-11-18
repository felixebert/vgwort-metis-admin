package de.ifcore.metis.admin.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextTest
{
	@Test(expected = IllegalStateException.class)
	public void shouldPreventPixelUpdate()
	{
		Text text = new Text("bla", null);
		text.setPixel(new Pixel("1", "2", "3"));
		text.setPixel(new Pixel("1", "2", "3"));
	}

	@Test
	public void shouldSetPixel()
	{
		Text text = new Text("bla", null);
		Pixel pixel = new Pixel("1", "2", "3");
		text.setPixel(pixel);
		assertEquals(pixel, text.getPixel());
	}
}
