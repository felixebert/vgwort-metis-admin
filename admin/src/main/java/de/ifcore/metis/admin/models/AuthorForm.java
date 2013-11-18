package de.ifcore.metis.admin.models;

import org.hibernate.validator.constraints.NotBlank;

import de.ifcore.metis.admin.entities.Author;
import de.ifcore.metis.admin.entities.AuthorAlias;

public class AuthorForm
{
	@NotBlank
	private String alias;

	@NotBlank
	private String firstname;

	@NotBlank
	private String lastname;

	private String cardNumber;

	public static AuthorForm copyOf(Author author)
	{
		AuthorForm form = new AuthorForm();
		form.setAlias(author.getAliases().get(0).getName());
		form.setCardNumber(author.getCardNumber());
		form.setFirstname(author.getFirstname());
		form.setLastname(author.getLastname());
		return form;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public Author toAuthor()
	{
		Author author = new Author(firstname, lastname, cardNumber);
		author.addAlias(new AuthorAlias(alias, author));
		return author;
	}

	public void updateAuthor(Author author)
	{
		author.setFirstname(firstname);
		author.setLastname(lastname);
		author.setCardNumber(cardNumber);
	}
}
