package de.ifcore.metis.admin.pixelserver;

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
	private static final String EMPTY_RESPONSE = "";
	private static final Logger log = LoggerFactory.getLogger(ServePixelController.class);

	private final PixelServer pixelServer;

	@Inject
	public ServePixelController(PixelServer pixelServer)
	{
		this.pixelServer = pixelServer;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String get(RawPixelRequest rawPixelRequest)
	{
		try
		{
			String publicPixelId = rawPixelRequest.process().getPublicPixelId(pixelServer);
			return generateResponse(publicPixelId);
		}
		catch (Exception e)
		{
			log.error("couldn't determine publicPixelId for pixelRequest " + rawPixelRequest, e);
			return EMPTY_RESPONSE;
		}
	}

	public String generateResponse(String publicPixelId)
	{
		if (publicPixelId == null)
		{
			log.trace("returning empty response");
			return EMPTY_RESPONSE;
		}
		else
		{
			log.trace("returning vgwort pixel");
			String src = "domain.met.vgwort.de/na/" + publicPixelId;
			String imgTag = "<img src=\"" + src + "\" height=\"1\" width=\"1\" border=\"0\" />";
			return "document.write('" + imgTag + "')";
		}
	}
}
