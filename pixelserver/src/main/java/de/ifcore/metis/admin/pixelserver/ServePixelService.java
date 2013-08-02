package de.ifcore.metis.admin.pixelserver;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServePixelService
{
	private static final String EMPTY_RESPONSE = "";
	private static final Logger log = LoggerFactory.getLogger(ServePixelService.class);

	private final PixelServer pixelServer;

	@Inject
	public ServePixelService(PixelServer pixelServer)
	{
		this.pixelServer = pixelServer;
	}

	public String getResponse(PageRequest pageRequest)
	{
		try
		{
			Page page = pageRequest.toPage();
			String publicPixelId = page.getPublicPixelId(pixelServer);
			return publicPixelId == null ? EMPTY_RESPONSE : publicPixelId;
		}
		catch (Exception e)
		{
			log.error("couldn't determine publicPixelId for pageRequest", e);
			return EMPTY_RESPONSE;
		}
	}
}
