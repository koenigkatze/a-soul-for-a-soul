package com.koenigkatze.asoulforasoul.media.sound;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.repositories.Repository;
import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

import com.badlogic.gdx.audio.Sound;

public final class SoundRepository implements Repository<Sound>
{
	private final Map<RepositoryKey, Sound> mapping;
	private final String name;

	public SoundRepository(final String name)
	{
		super();
		this.name = name;
		mapping = new ConcurrentHashMap<>();
	}

	@Override
	public void put(final RepositoryKey key, final Sound value)
	{
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		mapping.put(key, value);
	}

	@Override
	public Sound get(final RepositoryKey key)
	{
		Objects.requireNonNull(key);
		return mapping.get(key);
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "SoundRepository [name=" + name + "]";
	}

}
