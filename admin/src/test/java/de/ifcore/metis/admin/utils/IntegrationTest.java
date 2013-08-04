package de.ifcore.metis.admin.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.PixelLinkDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.PixelLink;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(locations = { "/applicationContext.xml" })
public abstract class IntegrationTest
{
	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private PixelDao pixelDao;

	@Autowired
	private PixelLinkDao pixelLinkDao;

	protected void flush()
	{
		sessionFactory.getCurrentSession().flush();
	}

	protected void flushAndClear()
	{
		flush();
		sessionFactory.getCurrentSession().clear();
	}

	protected Pixel persistPixel(Pixel pixel)
	{
		pixelDao.save(pixel);
		return pixel;
	}

	protected PixelLink persistPixelLink(PixelLink pixelLink)
	{
		pixelLinkDao.save(pixelLink);
		return pixelLink;
	}
}
