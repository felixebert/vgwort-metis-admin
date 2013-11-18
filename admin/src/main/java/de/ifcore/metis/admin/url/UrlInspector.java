package de.ifcore.metis.admin.url;

import de.ifcore.metis.admin.entities.TextUrl;
import de.ifcore.metis.grabber.GrabbedData;

public interface UrlInspector
{
	public void inspect(String url);

	public void inspect(GrabbedData data);

	public void inspectUnknownUrl(GrabbedData data);

	public void inspectKnownUrl(TextUrl url, GrabbedData data);
}
