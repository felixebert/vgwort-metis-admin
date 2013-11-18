package de.ifcore.metis.admin.pixel;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextUrlDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.admin.url.UrlScheduler;
import de.ifcore.metis.pixelserver.PixelDigest;
import de.ifcore.metis.pixelserver.PixelServer;

@Named
public class PixelServerImpl implements PixelServer
{
	private static final Logger log = LoggerFactory.getLogger(PixelServerImpl.class);

	private final TextUrlDao textUrlDao;
	private final UrlScheduler urlAssigner;

	@Inject
	public PixelServerImpl(TextUrlDao textUrlDao, UrlScheduler urlAssigner)
	{
		this.textUrlDao = textUrlDao;
		this.urlAssigner = urlAssigner;
	}

	@Override
	@Transactional(readOnly = true)
	public PixelDigest getPixel(String url)
	{
		TextUrl textUrl = textUrlDao.get(url);

		if (textUrl == null || textUrl.getText().getPixel() == null)
		{
			log.trace("no pixel assigned to url " + url);
			return null;
		}
		else
		{
			Pixel pixelEntity = textUrl.getText().getPixel();
			log.trace("returning assigned pixel for url " + url);
			return new PixelDigest(pixelEntity.getHost(), pixelEntity.getPublicId());
		}
	}

	@Override
	public void assignPixelTo(String url)
	{
		urlAssigner.offer(url);
	}
}
