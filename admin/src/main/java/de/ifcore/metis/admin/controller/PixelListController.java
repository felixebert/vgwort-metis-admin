package de.ifcore.metis.admin.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.entities.PixelLink;
import de.ifcore.metis.admin.services.PixelListService;

@Controller
public class PixelListController
{
	private final PixelListService pixelListService;

	@Inject
	public PixelListController(PixelListService pixelListService)
	{
		this.pixelListService = pixelListService;
	}

	@RequestMapping(value = "/pixelLinks", method = RequestMethod.GET)
	@ResponseBody
	public Collection<PixelLink> getPixelLinks()
	{
		return pixelListService.getAllPixelLinks();
	}
}
