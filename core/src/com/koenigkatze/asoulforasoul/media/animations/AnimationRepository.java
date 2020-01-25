package com.koenigkatze.asoulforasoul.media.animations;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.repositories.Repository;
import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AnimationRepository implements Repository<Animation<AtlasRegion>>
{
	private final Map<RepositoryKey, Animation<AtlasRegion>> mapping;
	private final String name;

	public AnimationRepository(final String name)
	{
		super();
		this.name = name;
		mapping = new ConcurrentHashMap<>();
	}

	@Override
	public void put(RepositoryKey key,  Animation<AtlasRegion> value)
	{
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		mapping.put(key, value);
	}

	@Override
	public  Animation<AtlasRegion> get(RepositoryKey key)
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
		return "AnimationRepository [name=" + name + "]";
	}
	
	

}
