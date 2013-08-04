package de.ifcore.metis.admin.dao.hibernate;

import static de.ifcore.metis.admin.utils.EntityTestUtils.mockPixel;
import static de.ifcore.metis.admin.utils.EntityTestUtils.mockPixelLink;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.ifcore.metis.admin.entities.Pixel;
import de.ifcore.metis.admin.utils.IntegrationTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class HibernatePixelDaoTest extends IntegrationTest
{
	@Test
	public void shouldListUnlinkedPixels()
	{
		persistPixel(mockPixel());
		flushAndClear();

		assertEquals(pixelDao.getUnlinkedPixels().size(), 1);
	}

	@Test
	public void shouldNotListLinkedPixels()
	{
		Pixel pixel = persistPixel(mockPixel());
		persistPixelLink(mockPixelLink(pixel.getPublicId()));
		flushAndClear();

		assertEquals(pixelDao.getUnlinkedPixels().size(), 0);
	}

	@Test
	public void shouldNeverReturnNull()
	{
		assertEquals(pixelDao.getUnlinkedPixels().size(), 0);
	}
}
