package de.ifcore.metis.admin.services;

import java.util.List;

import de.ifcore.metis.admin.entities.Text;
import de.ifcore.metis.grabber.GrabbedData;

public interface TextService
{
	public Text get(String id);

	public List<String> getUrls(String id);

	public GrabbedData grab(String id);

}
