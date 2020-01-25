package com.koenigkatze.asoulforasoul.entities.debug;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class DebugComponent implements Component, Poolable
{
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private World world;

	public Box2DDebugRenderer getDebugRenderer()
	{
		return debugRenderer;
	}

	public void setDebugRenderer(final Box2DDebugRenderer debugRenderer)
	{
		this.debugRenderer = debugRenderer;
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public void setCamera(final OrthographicCamera camera)
	{
		this.camera = camera;
	}

	public World getWorld()
	{
		return world;
	}

	public void setWorld(final World world)
	{
		this.world = world;
	}

	@Override
	public void reset()
	{
		
	}

}
