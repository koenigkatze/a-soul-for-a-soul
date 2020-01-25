package com.koenigkatze.asoulforasoul.entities.positions;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class PositionComponent implements Component, Poolable
{
	private Vector3 position;

	public PositionComponent(final Vector3 position)
	{
		super();
		this.position = position;
	}

	public Vector3 getPosition()
	{
		return position;
	}

	public void setPosition(final Vector3 position)
	{
		this.position = position;
	}

	public void setPositionIn2d(final float positionX, final float positionY)
	{
		this.position.x = positionX;
		this.position.y = positionY;
	}

	@Override
	public void reset()
	{
		// Do nothing.
	}

}
