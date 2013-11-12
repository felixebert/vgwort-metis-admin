package de.ifcore.metis.admin.services;

import java.util.Collection;

import de.ifcore.metis.admin.entities.Text;

public interface TextListService
{
	public Collection<Text> getTexts();
}