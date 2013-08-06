package de.ifcore.metis.admin.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.ifcore.metis.admin.entities.Pixel;

public class CustomObjectMapperTest
{
	@Test
	public void shouldSerializeDateFieldsInIsoFormat() throws JsonProcessingException
	{
		CustomObjectMapper objectMapper = new CustomObjectMapper();
		Date date = new DateTime(2013, 8, 6, 20, 0).toDate();
		Pixel pixel = new Pixel("abc", "abc", "abc");
		ReflectionTestUtils.setField(pixel, "createdAt", date);

		String expected = "{\"publicId\":\"abc\",\"privateId\":\"abc\",\"host\":\"abc\",\"createdAt\":\"2013-08-06T20:00:00.000+02:00\"}";
		assertEquals(expected, objectMapper.writeValueAsString(pixel));
	}
}
