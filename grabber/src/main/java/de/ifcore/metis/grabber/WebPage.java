package de.ifcore.metis.grabber;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
		builder.setId(getId());
		builder.setLyric(getLyric());
		builder.setPageUrls(getPageUrls());
		builder.setPdfUrl(getPdfUrl());
		builder.setText(getText());
		builder.setTitle(getText());
		builder.setTranslators(getTranslators());
		builder.setAuthors(getAuthors());
		return builder.build();
	}

	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getPdfUrl()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getPageUrls()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean getLyric()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getId()
	{
		Element metaTag = document.select("meta[name=vgw-id]").first();
		return metaTag == null ? null : metaTag.attr("content");
	}

	public List<String> getTranslators()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAuthors()
	{
		return null;
	}
}
