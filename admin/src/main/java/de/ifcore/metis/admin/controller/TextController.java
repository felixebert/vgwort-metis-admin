package de.ifcore.metis.admin.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.services.TextService;
import de.ifcore.metis.grabber.GrabbedData;

@Controller
public class TextController
{
	private final TextService textService;

	@Inject
	public TextController(TextService textService)
	{
		this.textService = textService;
	}

	@RequestMapping(value = "/text/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Text getText(@PathVariable String id)
	{
		return textService.get(id);
	}

	@RequestMapping(value = "/textUrls/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getTextUrls(@PathVariable String id)
	{
		return textService.getUrls(id);
	}

	@RequestMapping(value = "/grab/{id}", method = RequestMethod.GET)
	@ResponseBody
	public GrabbedData grab(@PathVariable String id)
	{
		return textService.grab(id);
	}
}
