package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity
public class SubmissionAttempt
{
	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false, updatable = false)
	private String checksum;

	@Column(nullable = false, updatable = false)
	private String error;

	@ManyToOne
	@JoinColumn(nullable = false, updatable = false)
	private Text text;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	SubmissionAttempt()
	{
	}

	public SubmissionAttempt(String checksum, String error, Text text)
	{
		this.checksum = checksum;
		this.error = error;
		this.text = text;
		this.createdAt = new Date();
	}

	public Long getId()
	{
		return id;
	}

	public String getChecksum()
	{
		return checksum;
	}

	public String getError()
	{
		return error;
	}

	public Text getText()
	{
		return text;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	@Override
	public String toString()
	{
		return "SubmissionAttempt [id=" + id + ", checksum=" + checksum + ", error=" + error + ", createdAt="
				+ createdAt + "]";
	}
}
