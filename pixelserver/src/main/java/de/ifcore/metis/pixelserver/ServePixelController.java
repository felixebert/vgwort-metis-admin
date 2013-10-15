package de.ifcore.metis.pixelserver;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/servePixel")
public class ServePixelController
{
	private static final Logger log = LoggerFactory.getLogger(ServePixelController.class);

	private final PixelServer pixelServer;

	@Inject
	public ServePixelController(PixelServer pixelServer)
	{
		this.pixelServer = pixelServer;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/javascript")
	@ResponseBody
	public String get(RawPixelRequest rawPixelRequest)
	{
		try
		{
			PixelResponse response = rawPixelRequest.process().getResponse(pixelServer);
			return response.toString();
		}
		catch (Exception e)
		{
			log.error("couldn't determine publicPixelId for pixelRequest " + rawPixelRequest, e);
			return "";
		}
	}
}
