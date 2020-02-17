package com.koenigkatze.asoulforasoul.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

public final class LevelData {
	static class LevelDataBuilder {
		private final String levelName;
		private final OrthographicCamera camera;
		private final LevelInputController input;
		private SpriteBatch batch;
		private TiledMap tiledMap;

		private LevelDataBuilder(final String levelName, final OrthographicCamera camera,
				final LevelInputController input) {
			this.levelName = levelName;
			this.camera = camera;
			this.input = input;
		}

		public LevelDataBuilder withBatch(final SpriteBatch batch) {
			this.batch = batch;
			return this;
		}

		public LevelDataBuilder withTiledMap(final TiledMap tiledMap) {
			this.tiledMap = tiledMap;
			return this;
		}

		public LevelData build() {
			return new LevelData(levelName, camera, input, batch, tiledMap);
		}

	}

	private final String levelName;
	private final OrthographicCamera camera;
	private final LevelInputController input;
	private final SpriteBatch batch;
	private final TiledMap tiledMap;

	private LevelData(final String levelName, final OrthographicCamera camera, final LevelInputController input,
			final SpriteBatch batch, final TiledMap tiledMap) {
		this.levelName = levelName;
		this.camera = camera;
		this.input = input;
		this.batch = batch;
		this.tiledMap = tiledMap;
	}

	public static LevelDataBuilder builder(final String levelName, final OrthographicCamera camera,
			final LevelInputController input) {
		return new LevelDataBuilder(levelName, camera, input);
	}

	public String getLevelName() {
		return levelName;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public LevelInputController getInput() {
		return input;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

}
