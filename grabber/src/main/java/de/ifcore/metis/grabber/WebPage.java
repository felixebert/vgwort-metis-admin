package de.ifcore.metis.grabber;

import org.jsoup.nodes.Document;

import de.ifcore.metis.grabber.GrabbedData.Builder;

public class WebPage
{
	private final String url;
	private final Document document;

	public WebPage(String url, Document document)
	{
		this.url = url;
		this.document = document;
	}

	public Document getDocument()
	{
		return document;
	}

	public GrabbedData extractData()
	{
		Builder builder = new GrabbedData.Builder(url);
		builder.setResult(GrabResult.FOUND);
		return null;
	}
}
