package com.koenigkatze.asoulforasoul.media.animations;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.exceptions.MissingRepositoryException;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public final class AnimationRepositories
{
	private static final Map<String, Repository<Animation<AtlasRegion>>> MAPPING = new ConcurrentHashMap<>();

	public static Repository<Animation<AtlasRegion>> get(final String name)
	{
		if (!MAPPING.containsKey(name))
		{
			throw new MissingRepositoryException("Unknown repository: " + name);
		}
		return MAPPING.get(name);
	}

	public static void put(final String name, final Repository<Animation<AtlasRegion>> repository)
	{
		Objects.requireNonNull(name);
		Objects.requireNonNull(repository);
		MAPPING.put(name, repository);
	}

}
