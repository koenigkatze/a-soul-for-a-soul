package com.koenigkatze.asoulforasoul.level;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.koenigkatze.asoulforasoul.entities.debug.DebugComponent;
import com.koenigkatze.asoulforasoul.entities.debug.DebugSystem;
import com.koenigkatze.asoulforasoul.entities.directions.DirectionalComponent;
import com.koenigkatze.asoulforasoul.entities.interaction.CursorControlSystem;
import com.koenigkatze.asoulforasoul.entities.interaction.InteractionComponent;
import com.koenigkatze.asoulforasoul.entities.maps.MapRenderComponent;
import com.koenigkatze.asoulforasoul.entities.maps.MapRenderSystem;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;
import com.koenigkatze.asoulforasoul.entities.physics.BodyFactory;
import com.koenigkatze.asoulforasoul.entities.players.PlayerComponent;
import com.koenigkatze.asoulforasoul.entities.players.PlayerControlSystem;
import com.koenigkatze.asoulforasoul.entities.positions.PositionComponent;
import com.koenigkatze.asoulforasoul.entities.positions.PositionSystem;
import com.koenigkatze.asoulforasoul.entities.positions.VerticalAxisComparator;
import com.koenigkatze.asoulforasoul.entities.sounds.SoundComponent;
import com.koenigkatze.asoulforasoul.entities.sounds.SoundSystem;
import com.koenigkatze.asoulforasoul.entities.sprites.RenderSystem;
import com.koenigkatze.asoulforasoul.entities.sprites.SpriteProviderComponent;
import com.koenigkatze.asoulforasoul.entities.states.StateComponent;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.maps.general.MapData;
import com.koenigkatze.asoulforasoul.maps.general.MapLoader;
import com.koenigkatze.asoulforasoul.maps.general.MapObjectCreator;
import com.koenigkatze.asoulforasoul.messages.codes.LevelMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

public final class Level {
	private MapData mapData;

	private SpriteBatch batch;
	private World world;
	private OrthographicCamera camera;

	private BodyFactory bodyFactory;
	private PooledEngine entityEngine;
	private LevelInputController inputController;

	public void load() {
		inputController = new LevelInputController();
		Gdx.input.setInputProcessor(inputController);

		registerLevelEndpoint();

		loadLevelData();

		loadLevelSystems();

		loadMapObjects("test_2.tmx");

//		setUpAmbience();
		// Definitiv ver-controllern hier!!!
//		final MapEnvironmentTypes environment = mapProperties.getEnvironment();
//		final Sound sound = SoundRepositories.get("ambient").get(SoundKeys.FOOTSTEPS_GRASS);
//		final long id = sound.loop();
//		sound.setVolume(id, 0.25f);
	}

	private void loadLevelData() {
		Messages.publish(LevelMessageCodes.LEVEL_DATA_LOADING, "test_2");

		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.zoom = 0.35f;
		camera.update();

		world = new World(new Vector2(0, 0f), true);
		world.setContactListener(new LevelContactListener());

		entityEngine = new PooledEngine();
		bodyFactory = new BodyFactory(world);

		batch = new SpriteBatch();

		mapData = MapLoader.loadFromStandardPath("test_2");

		final LevelData levelData = LevelData.builder("test", camera, inputController).withWorld(world)
				.withEntityEngine(entityEngine).withBatch(batch).withTiledMap(mapData.getTiledMap()).build();

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

	private void loadMapObjects(final String mapName) {
		MapObjectCreator.createFromMap(mapData.getTiledMap());
	}

	private void loadLevelSystems() {
		entityEngine
				.addSystem(new PlayerControlSystem(Family
						.all(PlayerComponent.class, BodyComponent.class, StateComponent.class,
								DirectionalComponent.class, PositionComponent.class, SpriteProviderComponent.class)
						.get(), inputController));

		entityEngine.addSystem(new PositionSystem(Family.all(BodyComponent.class, StateComponent.class,
				DirectionalComponent.class, PositionComponent.class, SpriteProviderComponent.class).get()));

		final TiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(mapData.getTiledMap());
		entityEngine.addSystem(new MapRenderSystem(Family.all(MapRenderComponent.class, BodyComponent.class).get(),
				camera, tiledMapRenderer));

		entityEngine.addSystem(new RenderSystem(Family.all(SpriteProviderComponent.class, PositionComponent.class,
				StateComponent.class, PositionComponent.class).get(), camera, batch, new VerticalAxisComparator()));

		entityEngine.addSystem(new SoundSystem(Family.all(SoundComponent.class, StateComponent.class).get()));

		entityEngine.addSystem(new CursorControlSystem(Family.all(InteractionComponent.class).get(), camera));

		final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
		entityEngine.addSystem(new DebugSystem(Family.all(DebugComponent.class).get()));
		final DebugComponent debugComponent = entityEngine.createComponent(DebugComponent.class);
		debugComponent.setDebugRenderer(debugRenderer);
		debugComponent.setCamera(camera);
		debugComponent.setWorld(world);

		final Entity debugEntity = entityEngine.createEntity();
		debugEntity.add(debugComponent);
		entityEngine.addEntity(debugEntity);
	}

	public void update() {
		world.step(1f / 60f, 6, 2);
		entityEngine.update(Gdx.graphics.getDeltaTime());
	}

	public void resize() {
		batch.setProjectionMatrix(camera.combined);
	}

	public void dispose() {
		bodyFactory.dispose();
		world.dispose();
	}

}
