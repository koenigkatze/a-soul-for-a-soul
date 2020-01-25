package com.koenigkatze.asoulforasoul.entities.sprites;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public final class SimpleSpriteProvider implements SpriteProvider
{
	private final Sprite sprite;

	public SimpleSpriteProvider(final Sprite sprite)
	{
		this.sprite = sprite;
	}

	@Override
	public Sprite getSprite()
	{
		return sprite;
	}

	@Override
	public void update(final Vector3 position, final EntityStates state, final EntityDirections direction,
			final float deltaTime)
	{
		sprite.setPosition(position.x, position.y);
	}

}
