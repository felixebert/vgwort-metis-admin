package de.ifcore.metis.grabber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		builder.setTitle(getTitle());
		builder.setTranslators(getTranslators());
		builder.setAuthors(getAuthors());
		return builder.build();
	}

	public String getText()
	{
		Element element = document.select(".vgw-text").first();
		if (element != null)
		{
			return element.text();
		}
		return null;
	}

	public String getTitle()
	{
		Element element = document.select(".vgw-title").first();
		if (element != null)
		{
			return element.text();
		}
		element = document.getElementsByTag("title").first();
		if (element != null)
		{
			return element.text();
		}
		return null;
	}

	public String getPdfUrl()
	{
		Element linkToPdf = document.getElementsByClass("vgw-pdf").first();
		return linkToPdf == null ? null : linkToPdf.attr("href");
	}

	public List<String> getPageUrls()
	{
		List<String> pageUrls = new ArrayList<>();
		Elements elements = document.getElementsByClass("vgw-page");
		for (Element element : elements)
		{
			pageUrls.add(element.attr("href"));
		}
		return Collections.unmodifiableList(pageUrls);
	}

	public Boolean getLyric()
	{
		for (Element element : document.select(".vgw-text, .vgw-pdf"))
		{
			if ("true".equals(element.attr("data-lyric")))
			{
				return Boolean.TRUE;
			}
		}
		return null;
	}

	public String getId()
	{
		Element metaTag = document.select("meta[name=vgw-id]").first();
		return metaTag == null ? null : metaTag.attr("content");
	}

	public List<String> getTranslators()
	{
		return collectInvolved("translator");
	}

	public List<String> getAuthors()
	{
		return collectInvolved("author");
	}

	private List<String> collectInvolved(String role)
	{
		List<String> involved = new ArrayList<>();
		Elements elements = document.getElementsByClass("vgw-" + role);
		for (Element element : elements)
		{
			involved.add(element.text());
		}

		if (involved.isEmpty())
		{
			Element metaTag = document.select("meta[name=" + role + "]").first();
			if (metaTag != null)
			{
				involved.add(metaTag.attr("content"));
			}
		}

		return Collections.unmodifiableList(involved);
	}
}
