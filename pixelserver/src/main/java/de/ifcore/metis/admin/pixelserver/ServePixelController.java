package de.ifcore.metis.admin.pixelserver;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/servePixel")
public class ServePixelController
{
	private final ServePixelService service;

	@Inject
	public ServePixelController(ServePixelService service)
	{
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String get(PageRequest pageRequest)
	{
		return service.getResponse(pageRequest);
	}
}
