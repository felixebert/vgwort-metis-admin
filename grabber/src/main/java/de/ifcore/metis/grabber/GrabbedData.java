package de.ifcore.metis.grabber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrabbedData
{
	private final String source;
	private final GrabResult result;
	private final String id;
	private final List<String> authors;
	private final List<String> translators;
	private final String title;
	private final String text;
	private final String pdfUrl;
	private final Boolean lyric;
	private final List<String> pageUrls;

	public static GrabbedData error(String source, GrabResult result)
	{
		Builder builder = new Builder(source);
		builder.setResult(result);
		return builder.build();
	}

	private GrabbedData(Builder builder)
	{
		this.source = builder.source;
		this.result = builder.result;
		this.id = builder.id;
		this.authors = Collections.unmodifiableList(builder.authors);
		this.translators = Collections.unmodifiableList(builder.translators);
		this.title = builder.title;
		this.text = builder.text;
		this.pdfUrl = builder.pdfUrl;
		this.lyric = builder.lyric;
		this.pageUrls = Collections.unmodifiableList(builder.pageUrls);
	}

	public String getId()
	{
		return id;
	}

	public List<String> getAuthors()
	{
		return authors;
	}

	public List<String> getTranslators()
	{
		return translators;
	}

	public String getTitle()
	{
		return title;
	}

	public String getText()
	{
		return text;
	}

	public String getPdfUrl()
	{
		return pdfUrl;
	}

	public Boolean getLyric()
	{
		return lyric;
	}

	public List<String> getPageUrls()
	{
		return pageUrls;
	}

	public String getSource()
	{
		return source;
	}

	public GrabResult getResult()
	{
		return result;
	}

	public static class Builder
	{
		private final String source;
		private GrabResult result;
		private String id;
		private List<String> authors = new ArrayList<>();
		private List<String> translators = new ArrayList<>();
		private String title;
		private String text;
		private String pdfUrl;
		private Boolean lyric;
		private List<String> pageUrls = new ArrayList<>();

		public Builder(String source)
		{
			if (source == null)
			{
				throw new IllegalArgumentException();
			}
			this.source = source;
		}

		public GrabResult getResult()
		{
			return result;
		}

		public void setResult(GrabResult result)
		{
			this.result = result;
		}

		public String getSource()
		{
			return source;
		}

		public String getId()
		{
			return id;
		}

		public void setId(String id)
		{
			this.id = id;
		}

		public List<String> getAuthors()
		{
			return authors;
		}

		public void setAuthors(List<String> authors)
		{
			if (authors == null)
			{
				throw new IllegalArgumentException();
			}
			this.authors = authors;
		}

		public List<String> getTranslators()
		{
			return translators;
		}

		public void setTranslators(List<String> translators)
		{
			if (translators == null)
			{
				throw new IllegalArgumentException();
			}
			this.translators = translators;
		}

		public String getTitle()
		{
			return title;
		}

		public void setTitle(String title)
		{
			this.title = title;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}

		public String getPdfUrl()
		{
			return pdfUrl;
		}

		public void setPdfUrl(String pdfUrl)
		{
			this.pdfUrl = pdfUrl;
		}

		public Boolean getLyric()
		{
			return lyric;
		}

		public void setLyric(Boolean lyric)
		{
			this.lyric = lyric;
		}

		public List<String> getPageUrls()
		{
			return pageUrls;
		}

		public void setPageUrls(List<String> pageUrls)
		{
			if (pageUrls == null)
			{
				throw new IllegalArgumentException();
			}
			this.pageUrls = pageUrls;
		}

		public GrabbedData build()
		{
			return new GrabbedData(this);
		}
	}

	@Override
	public String toString()
	{
		return "GrabbedData [source=" + source + ", result=" + result + ", id=" + id + ", authors=" + authors
				+ ", translators=" + translators + ", title=" + title + ", text=" + text + ", pdfUrl=" + pdfUrl
				+ ", lyric=" + lyric + ", pageUrls=" + pageUrls + "]";
	}
}
