package de.ifcore.metis.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.ifcore.metis.admin.models.PixelStatistic;
import de.ifcore.metis.admin.services.PixelStatisticService;

@Controller
public class PixelStatisticController
{
	private final PixelStatisticService pixelStatisticService;

	@Inject
	public PixelStatisticController(PixelStatisticService pixelStatisticService)
	{
		this.pixelStatisticService = pixelStatisticService;
	}

	@RequestMapping(value = "/pixelStatistic", method = RequestMethod.GET)
	@ResponseBody
	public PixelStatistic getStatistic()
	{
		return pixelStatisticService.getStatistic();
	}
}
