package com.koenigkatze.asoulforasoul.entities.sprites;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class SpriteProviderComponent implements Component, Poolable
{
	private final SpriteProvider provider;

	public SpriteProviderComponent(final SpriteProvider provider)
	{
		this.provider = provider;
	}

	public Sprite getSprite()
	{
		return provider.getSprite();
	}

	public void update(final Vector3 position, final EntityStates state, final EntityDirections direction,
			final float deltaTime)
	{
		provider.update(position, state, direction, deltaTime);
	}

	@Override
	public void reset()
	{
		// Do nothing.
	}

}
