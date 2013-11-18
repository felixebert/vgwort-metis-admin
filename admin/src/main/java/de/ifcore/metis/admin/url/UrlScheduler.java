package de.ifcore.metis.admin.url;

public interface UrlScheduler
{
	public void offer(String url);

	public void execute();
}
