package de.ifcore.metis.grabber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		assertEquals("cheat-1234", webPage("gta.html").getId());
		assertNull(webPage("pdf.html").getId());
	}

	@Test
	public void testGetTitle() throws IOException
	{
		assertEquals("Grand Theft Auto - San Andreas - Cheats für PC", webPage("gta.html").getTitle());
		assertEquals("Richtiger Titel", webPage("title-override.html").getTitle());
		assertNull(webPage("pdf.html").getTitle());
	}

	@Test
	public void testGetLyric() throws IOException
	{
		assertEquals(Boolean.TRUE, webPage("lyric-text.html").getLyric());
		assertEquals(Boolean.TRUE, webPage("lyric-pdf.html").getLyric());
		assertNull(webPage("gta.html").getLyric());
	}

	@Test
	public void testGetAuthors() throws IOException
	{
		assertEquals(1, webPage("gta.html").getAuthors().size());
		assertEquals("René Meyer", webPage("gta.html").getAuthors().get(0));

		assertEquals(2, webPage("author-override.html").getAuthors().size());
		assertEquals("rm", webPage("author-override.html").getAuthors().get(0));

		assertNotNull(webPage("text.html").getAuthors());
		assertTrue(webPage("text.html").getAuthors().isEmpty());
	}

	@Test
	public void testGetTranslators() throws IOException
	{
		assertEquals(3, webPage("translators.html").getTranslators().size());
		assertEquals("rm", webPage("translators.html").getTranslators().get(0));

		assertNotNull(webPage("text.html").getTranslators());
		assertTrue(webPage("text.html").getTranslators().isEmpty());
	}

	@Test
	public void testGetPdfUrl() throws IOException
	{
		assertEquals("assets/test.pdf", webPage("pdf.html").getPdfUrl());
		assertNull(webPage("gta.html").getPdfUrl());
	}

	@Test
	public void testGetPageUrls() throws IOException
	{
		assertEquals(3, webPage("pagination.html").getPageUrls().size());
		assertEquals("page1.html", webPage("pagination.html").getPageUrls().get(0));

		assertNotNull(webPage("text.html").getPageUrls());
		assertTrue(webPage("text.html").getPageUrls().isEmpty());
	}

	@Test
	public void testGetText() throws IOException
	{
		String testText = "GTA V Cheats von rm Cheats, während des Spiels einzugeben: asdasdj unendlich Leben 1234 unendlich Energie";
		assertEquals(testText, webPage("text.html").getText());
		assertEquals("bla", webPage("lyric-text.html").getText());
		assertNull(webPage("pdf.html").getText());
	}

	private WebPage webPage(String filename) throws IOException
	{
		File file = getResource(filename);
		Document document = Jsoup.parse(file, null, "");
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
