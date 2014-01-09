package de.ifcore.metis.admin.models;

public class PixelStatistic
{
	private final long numberOfPixels;
	private final long numberOfLinkedPixels;
	private final long numberOfUnlinkedPixels;

	public PixelStatistic(long numberOfPixels, long numberOfLinkedPixels)
	{
		this.numberOfPixels = numberOfPixels;
		this.numberOfLinkedPixels = numberOfLinkedPixels;
		this.numberOfUnlinkedPixels = Math.max(0, numberOfPixels - numberOfLinkedPixels);
	}

	public long getNumberOfPixels()
	{
		return numberOfPixels;
	}

	public long getNumberOfLinkedPixels()
	{
		return numberOfLinkedPixels;
	}

	public long getNumberOfUnlinkedPixels()
	{
		return numberOfUnlinkedPixels;
	}

	@Override
	public String toString()
	{
		return "PixelStatistic [numberOfPixels=" + numberOfPixels + ", numberOfLinkedPixels=" + numberOfLinkedPixels
				+ "]";
	}
}
