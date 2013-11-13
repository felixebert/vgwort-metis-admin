package de.ifcore.metis.grabber;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class WebPageTest
{
	@Test
	public void testGetId() throws IOException
	{
		WebPage webPage = webPageFor("gta.html");
		assertEquals("cheat-1234", webPage.getId());
	}

	private WebPage webPageFor(String filename) throws IOException
	{
		File file = getResource(filename);
		Document document = Jsoup.parse(file, "UTF-8", "");
		return new WebPage(null, document);
	}

	private File getResource(String filename)
	{
		URL url = getClass().getResource("/" + filename);
		try
		{
			return new File(url.toURI());
		}
		catch (URISyntaxException e)
		{
			throw new IllegalStateException(e);
		}
	}
}
