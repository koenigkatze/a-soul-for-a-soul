package com.koenigkatze.asoulforasoul.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ai.msg.MessageManager;
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
import com.koenigkatze.asoulforasoul.game.setup.BasicMessagingSetup;
import com.koenigkatze.asoulforasoul.level.LevelData;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.maps.entities.MapEntityFactory;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;

public final class EntityManager {
	
	private World world;
	private PooledEngine entityEngine;
	

	public EntityManager() {
		super();
		world = new World(new Vector2(0, 0f), true);
		world.setContactListener(new EntityContactListener());
		entityEngine = new PooledEngine();
		
		registerEntityEndpoint();
	}

	public void update(float delta) {
		world.step(1f / 60f, 6, 2);
		entityEngine.update(delta);
	}

	private void registerEntityEndpoint()
	{
		final MessageEndpoint entityMessageEndpoint = new EntityMessageEndpoint(new EntityMessageRouter(new MapEntityFactory(world, entityEngine), this));
		final MessageManager messageManager = MessageManager.getInstance();
		for (final EntityMessageCodes entityCode : EntityMessageCodes.values())
		{
			messageManager.addListener(entityMessageEndpoint, entityCode.get());
		}
		for (final MessageCode entityCode : entityMessageEndpoint.getCodesToSuscribe())
		{
			messageManager.addListener(entityMessageEndpoint, entityCode.get());
		}
		
		Logging.logDebug(BasicMessagingSetup.class, "Entity message endpoint registered.");
	}
	
	public void loadLevelSystems(LevelData levelData) {
		entityEngine
				.addSystem(new PlayerControlSystem(Family
						.all(PlayerComponent.class, BodyComponent.class, StateComponent.class,
								DirectionalComponent.class, PositionComponent.class, SpriteProviderComponent.class)
						.get(), levelData.getInput()));

		entityEngine.addSystem(new PositionSystem(Family.all(BodyComponent.class, StateComponent.class,
				DirectionalComponent.class, PositionComponent.class, SpriteProviderComponent.class).get()));

		final TiledMapRenderer tiledMapRenderer = new OrthogonalTiledMapRenderer(levelData.getTiledMap());
		entityEngine.addSystem(new MapRenderSystem(Family.all(MapRenderComponent.class, BodyComponent.class).get(),
				levelData.getCamera(), tiledMapRenderer));
		
		entityEngine.addSystem(new RenderSystem(Family.all(SpriteProviderComponent.class, PositionComponent.class,
				StateComponent.class, PositionComponent.class).get(), levelData.getCamera(), levelData.getBatch(), new VerticalAxisComparator()));

		entityEngine.addSystem(new SoundSystem(Family.all(SoundComponent.class, StateComponent.class).get()));

		entityEngine.addSystem(new CursorControlSystem(Family.all(InteractionComponent.class).get(), levelData.getCamera()));

		final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
		entityEngine.addSystem(new DebugSystem(Family.all(DebugComponent.class).get()));
		final DebugComponent debugComponent = entityEngine.createComponent(DebugComponent.class);
		debugComponent.setDebugRenderer(debugRenderer);
		debugComponent.setCamera(levelData.getCamera());
		debugComponent.setWorld(world);

		final Entity debugEntity = entityEngine.createEntity();
		debugEntity.add(debugComponent);
		entityEngine.addEntity(debugEntity);
	}

	public PooledEngine getEngine() {
		return this.entityEngine;
	}

	public World getWorld() {
		return this.world;
	}
	
}
