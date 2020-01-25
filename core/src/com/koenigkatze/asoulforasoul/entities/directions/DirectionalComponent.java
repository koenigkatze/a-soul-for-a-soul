package com.koenigkatze.asoulforasoul.entities.directions;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class DirectionalComponent implements Component, Poolable
{
	private EntityDirections direction;

	public DirectionalComponent(final EntityDirections direction)
	{
		super();
		this.direction = direction;
	}

	public EntityDirections getDirection()
	{
		return direction;
	}

	public void setDirection(final EntityDirections direction)
	{
		this.direction = direction;
	}

	@Override
	public void reset()
	{
		direction = EntityDirections.DOWN;
	}

}
