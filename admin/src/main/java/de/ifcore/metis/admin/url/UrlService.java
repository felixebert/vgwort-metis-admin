package de.ifcore.metis.admin.url;

import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;

public interface UrlService
{
	public void register(GrabbedData data);

	public void update(TextUrl url, GrabbedData data);

	public void delete(TextUrl url);
}
