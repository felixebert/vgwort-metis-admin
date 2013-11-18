package de.ifcore.metis.admin.dao;

import java.util.List;

import de.ifcore.metis.admin.entities.Text;

public interface TextDao extends EntityCruDao<Text, String>
{
	public List<Text> getTextsWithoutPixel();
}
