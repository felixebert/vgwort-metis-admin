package de.ifcore.metis.admin.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.entities.Author;
import de.ifcore.metis.admin.services.AuthorListService;

@Controller
public class AuthorListController
{
	private final AuthorListService authorListService;

	@Inject
	public AuthorListController(AuthorListService authorListService)
	{
		this.authorListService = authorListService;
	}

	@RequestMapping(value = "/authorList", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Author> getAuthors()
	{
		return authorListService.getAuthors();
	}

	@RequestMapping(value = "/unknownAuthors", method = RequestMethod.GET)
	@ResponseBody
	public Collection<String> getUnknownAuthors()
	{
		return authorListService.getUnknownAuthors();
	}
}
