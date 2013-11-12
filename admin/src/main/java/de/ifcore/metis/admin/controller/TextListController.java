package de.ifcore.metis.admin.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.services.TextListService;

@Controller
public class TextListController
{
	private final TextListService textListService;

	@Inject
	public TextListController(TextListService textListService)
	{
		this.textListService = textListService;
	}

	@RequestMapping(value = "/textList", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Text> getTexts()
	{
		return textListService.getTexts();
	}
}
