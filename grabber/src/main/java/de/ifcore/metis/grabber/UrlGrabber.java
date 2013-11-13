package de.ifcore.metis.grabber;

import java.io.IOException;

import javax.inject.Named;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Named
public class UrlGrabber implements Grabber
{
	@Override
	public GrabbedData grab(String url)
	{
		try
		{
			Document document = fetchUrl(url);
			WebPage webPage = new WebPage(url, document);
			return webPage.extractData();
		}
		catch (HttpStatusException e)
		{
			GrabResult result = 404 == e.getStatusCode() ? GrabResult.NOT_FOUND : GrabResult.ERROR;
			return GrabbedData.error(url, result);
		}
		catch (IOException e)
		{
			return GrabbedData.error(url, GrabResult.ERROR);
		}
	}

	private Document fetchUrl(String url) throws IOException
	{
		Connection connection = Jsoup.connect(url);
		Document document = connection.get();
		return document;
	}
}
