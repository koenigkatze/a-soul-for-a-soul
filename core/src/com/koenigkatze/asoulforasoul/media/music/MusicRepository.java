package com.koenigkatze.asoulforasoul.media.music;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.exceptions.UnknownMediaException;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;
import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

import com.badlogic.gdx.audio.Music;

public class MusicRepository implements Repository<Music>
{
	private final Map<RepositoryKey, Music> mapping;
	private final String name;

	public MusicRepository(final String name)
	{
		super();
		this.name = name;
		mapping = new ConcurrentHashMap<>();
	}

	@Override
	public void put(RepositoryKey key, Music value)
	{
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		mapping.put(key, value);
	}

	@Override
	public Music get(RepositoryKey key)
	{
		Objects.requireNonNull(key);
		final Music musicFile = mapping.get(key);
		if (musicFile == null) {
			throw new UnknownMediaException("File not found: "+key);
		}
		return musicFile;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return "AnimationRepository [name=" + name + "]";
	}

}
