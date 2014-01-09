package de.ifcore.metis.admin.dao.hibernate;

import static de.ifcore.metis.admin.utils.EntityTestUtils.mockPixel;
import static de.ifcore.metis.admin.utils.EntityTestUtils.mockText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.admin.utils.IntegrationTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTextDaoTest extends IntegrationTest
{
	@Test
	public void shouldPersistTextUrls()
	{
		Text text = new Text("cheat-1", null);
		text.addUrl(new TextUrl("http://localhost", text));
		textDao.save(text);
		flushAndClear();

		Text persistedText = textDao.get(text.getId());
		assertNotNull(persistedText);
		assertEquals(1, persistedText.getUrls().size());
	}

	@Test
	public void shouldUpdateTextUrls()
	{
		Text text = persistText(mockText());
		flushAndClear();

		text = textDao.get(text.getId());
		TextUrl textUrl = new TextUrl("http://localhost/test", text);
		text.addUrl(textUrl);
		textUrlDao.save(textUrl);
		textDao.update(text);
		flushAndClear();

		Text persistedText = textDao.get(text.getId());
		assertEquals(1, persistedText.getUrls().size());
	}

	@Test
	public void shouldListTextsWithoutPixel()
	{
		persistText(mockText());
		persistText(mockText());
		persistText(mockText(mockPixel()));
		flushAndClear();

		assertEquals(2, textDao.getTextsWithoutPixel().size());
	}

	@Test
	public void shouldListTextsWithoutPixelAndWithoutReturningNull()
	{
		persistText(mockText(mockPixel()));
		flushAndClear();

		assertEquals(0, textDao.getTextsWithoutPixel().size());
	}

	@Test
	public void shouldListLatest()
	{
		persistText(mockText(mockPixel()));
		flushAndClear();

		assertEquals(1, textDao.getLatest().size());
	}
}
