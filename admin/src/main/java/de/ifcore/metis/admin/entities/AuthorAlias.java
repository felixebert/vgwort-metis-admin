package de.ifcore.metis.admin.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity
public class AuthorAlias
{
	@Id
	@Column(nullable = false, updatable = false)
	private String name;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Author author;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	AuthorAlias()
	{
	}

	public AuthorAlias(String name, Author author)
	{
		this.name = name;
		this.author = author;
		this.createdAt = new Date();
	}

	public String getName()
	{
		return name;
	}

	public Author getAuthor()
	{
		return author;
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	@Override
	public String toString()
	{
		return "AuthorAlias [name=" + name + ", createdAt=" + createdAt + "]";
	}
}
