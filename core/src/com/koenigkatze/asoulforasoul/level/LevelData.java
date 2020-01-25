package com.koenigkatze.asoulforasoul.level;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

public final class LevelData
{
	static class LevelDataBuilder
	{
		private final String levelName;
		private final OrthographicCamera camera;
		private final LevelInputController input;
		private World world;
		private PooledEngine entityEngine;
		private SpriteBatch batch;
		private TiledMap tiledMap;

		private LevelDataBuilder(final String levelName, final OrthographicCamera camera,
				final LevelInputController input)
		{
			this.levelName = levelName;
			this.camera = camera;
			this.input = input;
		}

		public LevelDataBuilder withWorld(final World world)
		{
			this.world = world;
			return this;
		}

		public LevelDataBuilder withEntityEngine(final PooledEngine entityEngine)
		{
			this.entityEngine = entityEngine;
			return this;
		}

		public LevelDataBuilder withBatch(final SpriteBatch batch)
		{
			this.batch = batch;
			return this;
		}

		public LevelDataBuilder withTiledMap(final TiledMap tiledMap)
		{
			this.tiledMap = tiledMap;
			return this;
		}

		public LevelData build()
		{
			return new LevelData(levelName, camera, input, world, entityEngine, batch, tiledMap);
		}

	}

	private final String levelName;
	private final OrthographicCamera camera;
	private final LevelInputController input;
	private final World world;
	private final PooledEngine entityEngine;
	private final SpriteBatch batch;
	private final TiledMap tiledMap;

	private LevelData(final String levelName, final OrthographicCamera camera, final LevelInputController input,
			final World world, final PooledEngine entityEngine, final SpriteBatch batch, final TiledMap tiledMap)
	{
		this.levelName = levelName;
		this.camera = camera;
		this.input = input;
		this.world = world;
		this.entityEngine = entityEngine;
		this.batch = batch;
		this.tiledMap = tiledMap;
	}

	public static LevelDataBuilder builder(final String levelName, final OrthographicCamera camera,
			final LevelInputController input)
	{
		return new LevelDataBuilder(levelName, camera, input);
	}

	public String getLevelName()
	{
		return levelName;
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}

	public LevelInputController getInput()
	{
		return input;
	}

	public World getWorld()
	{
		return world;
	}

	public PooledEngine getEntityEngine()
	{
		return entityEngine;
	}

	public SpriteBatch getBatch()
	{
		return batch;
	}

	public TiledMap getTiledMap()
	{
		return tiledMap;
	}

}
