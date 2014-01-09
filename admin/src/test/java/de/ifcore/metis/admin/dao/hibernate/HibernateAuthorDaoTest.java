package de.ifcore.metis.admin.dao.hibernate;

import static de.ifcore.metis.admin.utils.EntityTestUtils.mockAuthor;
import static de.ifcore.metis.admin.utils.EntityTestUtils.mockText;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.ifcore.metis.admin.utils.IntegrationTest;

@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateAuthorDaoTest extends IntegrationTest
{
	@Test
	public void shouldListUnknownAuthorNames()
	{
		persistText(mockText("kt"));
		persistText(mockText("rm"));
		persistText(mockText("sl"));
		persistAuthor(mockAuthor("rm"));
		persistAuthor(mockAuthor("sl"));
		flushAndClear();

		List<String> authorNames = authorDao.getUnknownAuthorNames();
		assertEquals(1, authorNames.size());
		assertEquals("kt", authorNames.get(0));
	}

	@Test
	public void shouldListUnknownAuthorNamesDistinct()
	{
		persistText(mockText("rm"));
		persistText(mockText("rm"));
		flushAndClear();

		List<String> authorNames = authorDao.getUnknownAuthorNames();
		assertEquals(1, authorNames.size());
		assertEquals("rm", authorNames.get(0));
	}
}
