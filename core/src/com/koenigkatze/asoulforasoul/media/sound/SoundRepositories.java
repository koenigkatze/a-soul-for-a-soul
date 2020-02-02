package com.koenigkatze.asoulforasoul.media.sound;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.exceptions.MissingRepositoryException;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.audio.Sound;

public class SoundRepositories
{
	private static final Map<String, Repository<Sound>> MAPPING = new ConcurrentHashMap<>();
	
	public static final String AMBIENCE_REPOSITORY_NAME = "ambience";
	public static final String ENTITIES_REPOSITORY_NAME = "entities";

	public static Repository<Sound> get(final String name)
	{
		if (!MAPPING.containsKey(name))
		{
			throw new MissingRepositoryException("Unknown repository: " + name);
		}
		return MAPPING.get(name);
	}

	public static void put(final String name, final Repository<Sound> repository)
	{
		Objects.requireNonNull(name);
		Objects.requireNonNull(repository);
		MAPPING.put(name, repository);
	}

	public static Repository<Sound> getEntityRepository()
	{
		if (!MAPPING.containsKey(ENTITIES_REPOSITORY_NAME))
		{
			throw new MissingRepositoryException("Unknown repository: " + ENTITIES_REPOSITORY_NAME);
		}
		return MAPPING.get(ENTITIES_REPOSITORY_NAME);
	}
}
