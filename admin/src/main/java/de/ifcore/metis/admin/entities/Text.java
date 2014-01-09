package de.ifcore.metis.admin.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.ifcore.metis.grabber.GrabbedData;

@Entity
public class Text
{
	@Id
	@Column(nullable = false, updatable = false)
	private String id;

	private String author;

	@OneToOne
	@JoinColumn(unique = true)
	private Pixel pixel;

	@OneToMany(mappedBy = "text", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<TextUrl> urls;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	private Date submittedAt;

	private Date submissionAttemptedAt;

	@Column(columnDefinition = "BIT")
	private boolean submissionSuspended;

	private String submissionError;

	Text()
	{
	}

	public Text(String id, String author)
	{
		this.id = id;
		this.author = author;
		this.createdAt = new Date();
	}

	public static Text forData(GrabbedData data)
	{
		Text text = new Text(data.getId(), extractMainAuthor(data));
		text.addUrl(new TextUrl(data.getSource(), text));
		return text;
	}

	private static String extractMainAuthor(GrabbedData data)
	{
		return data.getAuthors().isEmpty() ? null : data.getAuthors().get(0);
	}

	public void updateWith(GrabbedData data)
	{
		this.author = extractMainAuthor(data);
	}

	public void setPixel(Pixel pixel)
	{
		if (this.pixel != null)
		{
			throw new IllegalStateException();
		}
		this.pixel = pixel;
	}

	public String getId()
	{
		return id;
	}

	public Pixel getPixel()
	{
		return pixel;
	}

	public String getAuthor()
	{
		return author;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	public List<TextUrl> getUrls()
	{
		return Collections.unmodifiableList(urls);
	}

	public void addUrl(TextUrl url)
	{
		if (urls == null)
		{
			urls = new ArrayList<>();
		}
		urls.add(url);
	}

	public DateTime getSubmittedAt()
	{
		return new DateTime(submittedAt);
	}

	public void setSubmittedAt(DateTime submittedAt)
	{
		this.submittedAt = submittedAt.toDate();
	}

	public DateTime getSubmissionAttemptedAt()
	{
		return new DateTime(submissionAttemptedAt);
	}

	public void setSubmissionAttemptedAt(DateTime submissionAttemptedAt)
	{
		this.submissionAttemptedAt = submissionAttemptedAt.toDate();
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public boolean isSubmissionSuspended()
	{
		return submissionSuspended;
	}

	public void setSubmissionSuspended(boolean submissionSuspended)
	{
		this.submissionSuspended = submissionSuspended;
	}

	public String getSubmissionError()
	{
		return submissionError;
	}

	public void setSubmissionError(String submissionError)
	{
		this.submissionError = submissionError;
	}

	@Override
	public String toString()
	{
		return "Text [id=" + id + ", author=" + author + ", createdAt=" + createdAt + "]";
	}
}
