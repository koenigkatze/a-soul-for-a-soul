package com.koenigkatze.asoulforasoul.entities.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class BodyComponent implements Component, Poolable
{
	private Body body;

	public BodyComponent(final Body body)
	{
		super();
		this.body = body;
	}

	public Body getBody()
	{
		return body;
	}

	public void setBody(final Body body)
	{
		this.body = body;
	}

	@Override
	public void reset()
	{
		// Do nothing.
	}

}
