package de.ifcore.metis.admin.url;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;

@Named
public class UrlSchedulerImpl implements UrlScheduler
{
	private final UrlInspector urlInspector;

	private final Queue<String> queue = new LinkedBlockingQueue<>(500);
	private final Set<String> blacklist = new HashSet<>();
	private DateTime nextReset;

	@Inject
	public UrlSchedulerImpl(UrlInspector urlInspector)
	{
		this.urlInspector = urlInspector;
		setNextReset();
	}

	@Override
	public void offer(String url)
	{
		maintainBlacklist();
		if (!blacklist.contains(url))
		{
			queue.offer(url);
		}
	}

	@Override
	@Scheduled(fixedDelay = 5000)
	public void execute()
	{
		String url = queue.poll();
		if (url != null)
		{
			blacklist.add(url);
			urlInspector.inspect(url);
		}
	}

	public void maintainBlacklist()
	{
		if (DateTime.now().isAfter(nextReset))
		{
			blacklist.clear();
			setNextReset();
		}
	}

	void setNextReset()
	{
		nextReset = DateTime.now().plusHours(24);
	}
}
