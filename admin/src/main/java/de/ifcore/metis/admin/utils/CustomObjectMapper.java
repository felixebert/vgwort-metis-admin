package de.ifcore.metis.admin.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class CustomObjectMapper extends ObjectMapper
{
	private static final long serialVersionUID = 5259384596213695789L;

	public CustomObjectMapper()
	{
		registerModule(new JodaModule());
		setDateFormat(new ISO8601DateFormat());
	}
}
