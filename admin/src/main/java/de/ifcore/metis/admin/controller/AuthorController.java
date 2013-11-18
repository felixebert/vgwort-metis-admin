package de.ifcore.metis.admin.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.models.AuthorForm;
import de.ifcore.metis.admin.services.AuthorService;

@Controller
public class AuthorController
{
	private final AuthorService authorService;

	@Inject
	public AuthorController(AuthorService authorService)
	{
		this.authorService = authorService;
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AuthorForm getAuthor(@PathVariable Long id)
	{
		return authorService.getAuthorForm(id);
	}

	@RequestMapping(value = "/author", method = RequestMethod.POST)
	@ResponseBody
	public AuthorForm addAuthor(@Valid AuthorForm form, Errors errors)
	{
		if (!errors.hasErrors())
		{
			authorService.persist(form);
		}
		else
		{
			throw new IllegalArgumentException(errors.toString());
		}
		return form;
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public AuthorForm updateAuthor(@PathVariable Long id, @Valid AuthorForm form, Errors errors)
	{
		if (!errors.hasErrors())
		{
			authorService.update(id, form);
		}
		else
		{
			throw new IllegalArgumentException(errors.toString());
		}
		return form;
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteAuthor(@PathVariable Long id)
	{
		authorService.delete(id);
	}
}
