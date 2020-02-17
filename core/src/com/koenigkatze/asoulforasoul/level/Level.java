package com.koenigkatze.asoulforasoul.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.maps.general.MapData;
import com.koenigkatze.asoulforasoul.maps.general.MapDataLoader;
import com.koenigkatze.asoulforasoul.maps.general.MapObjectCreator;
import com.koenigkatze.asoulforasoul.messages.codes.LevelMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

public final class Level {
	private MapData mapData;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private LevelInputController inputController;

	public void load(final String name) {
		inputController = new LevelInputController();
		Gdx.input.setInputProcessor(inputController);

		registerLevelEndpoint();

		loadLevelData(name);

		createMapObjects();

//		setUpAmbience();
		// Definitiv ver-controllern hier!!!
//		final MapEnvironmentTypes environment = mapProperties.getEnvironment();
//		final Sound sound = SoundRepositories.get("ambient").get(SoundKeys.FOOTSTEPS_GRASS);
//		final long id = sound.loop();
//		sound.setVolume(id, 0.25f);
	}

	private void loadLevelData(final String name) {
		Messages.publish(LevelMessageCodes.LEVEL_DATA_LOADING, name);

		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.zoom = 0.35f;
		camera.update();

		batch = new SpriteBatch();

		mapData = MapDataLoader.loadFromStandardPath(name);

		final LevelData levelData = LevelData.builder("test", camera, inputController).withBatch(batch)
				.withTiledMap(mapData.getTiledMap()).build();

		Messages.publish(LevelMessageCodes.LEVEL_DATA_LOADED, levelData);
	}

	private void registerLevelEndpoint() {
		final MessageManager messageManager = MessageManager.getInstance();
		final MessageEndpoint levelMessageEndpoint = new LevelMessageEndpoint(inputController);
		for (final MessageCode levelCodes : levelMessageEndpoint.getCodesToSuscribe()) {
			messageManager.addListener(levelMessageEndpoint, levelCodes.get());
		}
		for (final LevelMessageCodes levelCode : LevelMessageCodes.values()) {
			messageManager.addListener(levelMessageEndpoint, levelCode.get());
		}
		Logging.logDebug(Level.class, "Level message endpoint registered.");
	}

	private void createMapObjects() {
		MapObjectCreator.createFromMap(mapData.getTiledMap());
	}

	public void resize() {
		batch.setProjectionMatrix(camera.combined);
	}

	public void dispose() {
		batch.dispose();
	}

}
