package de.ifcore.metis.admin.url;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.admin.services.PixelPool;
import de.ifcore.metis.grabber.GrabbedData;

@Named
public class UrlServiceImpl implements UrlService
{
	private static final Logger log = LoggerFactory.getLogger(UrlServiceImpl.class);

	private final TextDao textDao;
	private final PixelPool pixelPool;

	@Inject
	public UrlServiceImpl(TextDao textDao, PixelPool pixelPool)
	{
		this.textDao = textDao;
		this.pixelPool = pixelPool;
	}

	@Override
	@Transactional
	public void register(GrabbedData data)
	{
		Text text = textDao.get(data.getId());
		if (text == null)
		{
			Pixel pixel = pixelPool.poll();
			if (pixel == null)
			{
				log.trace("couldn't assign a pixel to text " + data.getId());
			}
			else
			{
				log.trace("assigning a pixel to text " + data.getId());
				textDao.save(Text.forData(data, pixel));
			}
		}
		else
		{
			textDao.update(text.updateWith(data));
		}
	}

	@Override
	@Transactional
	public void update(TextUrl url, GrabbedData data)
	{
		if (!url.getText().getId().equals(data.getId()))
		{
			// update text (remove textUrl)
			// then METHOD 1
		}
		else
		{
			// update text
		}
	}

	@Override
	@Transactional
	public void delete(TextUrl url)
	{
		// TODO Auto-generated method stub

	}

}
