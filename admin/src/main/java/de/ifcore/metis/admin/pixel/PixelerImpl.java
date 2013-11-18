package de.ifcore.metis.admin.pixel;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;

@Named
public class PixelerImpl implements Pixeler
{
	private static final Logger log = LoggerFactory.getLogger(PixelerImpl.class);

	private final PixelDao pixelDao;
	private final TextDao textDao;

	@Inject
	public PixelerImpl(PixelDao pixelDao, TextDao textDao)
	{
		this.pixelDao = pixelDao;
		this.textDao = textDao;
	}

	@Override
	@Transactional
	public void pixel(Set<Text> texts)
	{
		Iterator<Pixel> pixels = pixelDao.getUnlinkedPixels().iterator();
		for (Text text : texts)
		{
			if (pixels.hasNext())
			{
				pixel(text, pixels.next());
			}
			else
			{
				log.trace("couldn't pixel all texts");
				break;
			}
		}
	}

	private void pixel(Text text, Pixel pixel)
	{
		log.trace("linking a pixel to text " + text.getId());
		text.setPixel(pixel);
		textDao.update(text);
	}
}
