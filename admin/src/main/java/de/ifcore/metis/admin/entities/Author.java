package de.ifcore.metis.admin.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author
{
	@Id
	@GeneratedValue
	@Column(nullable = false, updatable = false)
	private Long id;

	private String firstname;

	private String lastname;

	@Column(unique = true)
	private String cardNumber;

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<AuthorAlias> aliases;

	Author()
	{
	}

	public Author(String firstname, String lastname, String cardNumber)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.cardNumber = cardNumber;
		this.createdAt = new Date();
	}

	public Long getId()
	{
		return id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public List<AuthorAlias> getAliases()
	{
		return Collections.unmodifiableList(aliases);
	}

	public void addAlias(AuthorAlias alias)
	{
		if (aliases == null)
		{
			aliases = new ArrayList<>();
		}
		aliases.add(alias);
	}

	public DateTime getCreatedAt()
	{
		return new DateTime(createdAt);
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString()
	{
		return "Author [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", cardNumber="
				+ cardNumber + ", createdAt=" + createdAt + "]";
	}
}
