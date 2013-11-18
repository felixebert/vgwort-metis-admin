package de.ifcore.metis.admin.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.PixelDao;
import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.entities.Text;

@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(locations = { "/applicationContext.xml" })
public abstract class IntegrationTest
{
	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	protected PixelDao pixelDao;

	@Autowired
	protected TextDao textDao;

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

	protected Text persistText(Text text)
	{
		if (text.getPixel() != null)
		{
			pixelDao.save(text.getPixel());
		}
		textDao.save(text);
		return text;
	}
}
