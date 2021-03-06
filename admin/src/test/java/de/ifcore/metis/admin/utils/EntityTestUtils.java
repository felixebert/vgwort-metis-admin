package de.ifcore.metis.admin.utils;

import org.apache.commons.lang3.RandomStringUtils;

import de.ifcore.metis.admin.entities.Author;
import de.ifcore.metis.admin.entities.AuthorAlias;
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
		return new Text(rnd(10), rnd(10));
	}

	public static Text mockText(String author)
	{
		Text text = mockText();
		text.setAuthor(author);
		return text;
	}

	public static Text mockText(Pixel pixel)
	{
		Text text = new Text(rnd(10), rnd(10));
		text.setPixel(pixel);
		return text;
	}

	public static Author mockAuthor()
	{
		return new Author(rnd(10), rnd(10), null);
	}

	public static Author mockAuthor(String alias)
	{
		Author author = new Author(rnd(10), rnd(10), null);
		author.addAlias(new AuthorAlias(alias, author));
		return author;
	}

	private static String rnd(int count)
	{
		return RandomStringUtils.randomAlphabetic(count);
	}
}
