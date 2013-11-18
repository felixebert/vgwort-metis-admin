package de.ifcore.metis.admin.pixel;

import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import de.ifcore.metis.admin.dao.TextDao;
import de.ifcore.metis.admin.entities.Text;

@Named
public class PixelerSchedulerImpl implements PixelerScheduler
{
	private final TextDao textDao;
	private final Pixeler pixeler;

	@Inject
	public PixelerSchedulerImpl(TextDao textDao, Pixeler pixeler)
	{
		this.textDao = textDao;
		this.pixeler = pixeler;
	}

	@Override
	@Scheduled(fixedDelay = 5 * 60 * 1000)
	@Transactional
	public void execute()
	{
		List<Text> texts = textDao.getTextsWithoutPixel();
		if (!texts.isEmpty())
		{
			pixeler.pixel(new HashSet<>(texts));
		}
	}
}
