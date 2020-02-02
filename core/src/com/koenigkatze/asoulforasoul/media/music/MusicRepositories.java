package com.koenigkatze.asoulforasoul.media.music;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.exceptions.MissingRepositoryException;

public class MusicRepositories
{
	private static final Map<String, MusicRepository> MAPPING = new ConcurrentHashMap<>();
	
	public static final String STANDARD_REPOSITORY_NAME = "standard";

	public static MusicRepository get(final String name)
	{
		if (!MAPPING.containsKey(name))
		{
			throw new MissingRepositoryException("Unknown repository: " + name);
		}
		return MAPPING.get(name);
	}

	public static void put(final String name, final MusicRepository repository)
	{
		Objects.requireNonNull(name);
		Objects.requireNonNull(repository);
		MAPPING.put(name, repository);
	}

	public static MusicRepository getStandardRepository()
	{
		if (!MAPPING.containsKey(STANDARD_REPOSITORY_NAME))
		{
			throw new MissingRepositoryException("Unknown repository: " + STANDARD_REPOSITORY_NAME);
		}
		return MAPPING.get(STANDARD_REPOSITORY_NAME);
	}
}
