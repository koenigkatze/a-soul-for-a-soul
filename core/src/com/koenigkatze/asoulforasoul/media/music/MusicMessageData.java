package com.koenigkatze.asoulforasoul.media.music;

public final class MusicMessageData
{
	public static class MusicDataBuilder
	{
		private final String name;
		private float volume;
		private boolean looped;

		private MusicDataBuilder(final String track)
		{
			this.name = track;
		}

		public MusicDataBuilder withVolume(final float volume)
		{
			this.volume = volume;
			return this;
		}

		public MusicDataBuilder withLooped(final boolean looped)
		{
			this.looped = looped;
			return this;
		}

		public MusicMessageData build()
		{
			return new MusicMessageData(name, volume, looped);
		}
	}

	private final String name;
	private final float volume;
	private final boolean looped;

	private MusicMessageData(final String name, final float volume, final boolean looped)
	{
		super();
		this.name = name;
		this.volume = volume;
		this.looped = looped;
	}

	public static MusicDataBuilder builder(final String name)
	{
		return new MusicDataBuilder(name);
	}

	public String getName()
	{
		return name;
	}

	public float getVolume()
	{
		return volume;
	}

	public boolean isLooped()
	{
		return looped;
	}

}
