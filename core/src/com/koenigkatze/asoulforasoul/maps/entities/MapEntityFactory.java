package com.koenigkatze.asoulforasoul.maps.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.koenigkatze.asoulforasoul.constants.ConversionConstants;
import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.maps.MapRenderComponent;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;
import com.koenigkatze.asoulforasoul.entities.physics.BodyFactory;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.entities.utils.EntityBuilder;
import com.koenigkatze.asoulforasoul.level.InteractionBox;
import com.koenigkatze.asoulforasoul.level.InteractionTarget;
import com.koenigkatze.asoulforasoul.maps.general.BlockedObjectProperties;
import com.koenigkatze.asoulforasoul.maps.general.Coordinate2d;
import com.koenigkatze.asoulforasoul.maps.general.Dimension2d;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectProperties;
import com.koenigkatze.asoulforasoul.media.animations.AnimationKeys;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;
import com.koenigkatze.asoulforasoul.media.sound.SoundRepositories;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.UiMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;
import com.koenigkatze.asoulforasoul.ui.EntityIcons;
import com.koenigkatze.asoulforasoul.ui.UiMessageData;

public final class MapEntityFactory {
	private final PooledEngine entityEngine;
	private final BodyFactory bodyFactory;

	public MapEntityFactory(final PooledEngine entityEngine, final World world) {
		this.entityEngine = entityEngine;
		this.bodyFactory = new BodyFactory(world);
	}

	public void createBlockedObject(BlockedObjectProperties blockedObjectProperties) {
		bodyFactory.createRectangleBody(blockedObjectProperties.getPositionX(), blockedObjectProperties.getPositionY(),
				blockedObjectProperties.getWidth(), blockedObjectProperties.getHeight(), BodyType.StaticBody);
	}

	public void createPlayerEntity(final CharacterMapProperties characterProperties) {
		final Repository<Animation<AtlasRegion>> playerAnimationRepository = characterProperties
				.getAnimationRepository();
		final Sprite playerSprite = new Sprite(playerAnimationRepository
				.get(AnimationKeys.get(characterProperties.getState(), characterProperties.getDirection()))
				.getKeyFrame(0.0f));

		final Coordinate2d playerMapPosition = characterProperties.getPosition();
		final Body playerBody = bodyFactory.createCharacterBody(
				playerMapPosition.getX() / ConversionConstants.PIXELS_TO_METERS,
				playerMapPosition.getY() / ConversionConstants.PIXELS_TO_METERS,
				playerSprite.getWidth() / ConversionConstants.PIXELS_TO_METERS,
				(playerSprite.getHeight() - playerSprite.getHeight() / 3) / ConversionConstants.PIXELS_TO_METERS);

		final Vector3 playerPosition = new Vector3(playerMapPosition.getX(), playerMapPosition.getY(), 0);
		final Repository<Sound> soundRepository = SoundRepositories.getEntityRepository();

		final Entity playerEntity = EntityBuilder.get().withPlayerComponent().withBodyComponent(playerBody)
				.withPositionComponent(playerPosition).withStateComponent(characterProperties.getState())
				.withDirectionalComponent(characterProperties.getDirection())
				.withAnimationBasedSpriteProviderComponent(playerAnimationRepository)
				.withSoundComponent(soundRepository).buildWithEngine(entityEngine);
		entityEngine.addEntity(playerEntity);

		final Entity mapEntity = new Entity();
		mapEntity.add(new MapRenderComponent());
		mapEntity.add(new BodyComponent(playerBody));
		entityEngine.addEntity(mapEntity);
	}

	public void createNpcEntity(final CharacterMapProperties npcProperties) {
		final Repository<Animation<AtlasRegion>> npcAnimationRepository = npcProperties.getAnimationRepository();
		final Sprite npcSprite = new Sprite(npcAnimationRepository
				.get(AnimationKeys.get(npcProperties.getState(), npcProperties.getDirection())).getKeyFrame(0.0f));

		final Coordinate2d npcMapPosition = npcProperties.getPosition();
		final Body npcBody = bodyFactory.createCharacterBody(
				npcMapPosition.getX() / ConversionConstants.PIXELS_TO_METERS,
				npcMapPosition.getY() / ConversionConstants.PIXELS_TO_METERS,
				npcSprite.getWidth() / ConversionConstants.PIXELS_TO_METERS,
				(npcSprite.getHeight() - npcSprite.getHeight() / 3) / ConversionConstants.PIXELS_TO_METERS);

		final Vector3 npcPosition = new Vector3(npcMapPosition.getX(), npcMapPosition.getY(), 0);
		final Entity npcEntity = EntityBuilder.get().withBodyComponent(npcBody).withPositionComponent(npcPosition)
				.withStateComponent(npcProperties.getState()).withDirectionalComponent(npcProperties.getDirection())
				.withAnimationBasedSpriteProviderComponent(npcAnimationRepository).buildWithEngine(entityEngine);
		entityEngine.addEntity(npcEntity);
	}

	public void createPassiveMapEntity(final TiledMapTileMapObject mapObject) {
		final Sprite sprite = new Sprite(mapObject.getTextureRegion());
		final Entity entity = EntityBuilder.get().withSimpleSpriteProviderComponent(sprite)
				.withPositionComponent(new Vector3(mapObject.getX(), mapObject.getY(), 0))
				.withStateComponent(EntityStates.NONE).withDirectionalComponent(EntityDirections.NONE)
				.buildWithEngine(entityEngine);
		entityEngine.addEntity(entity);
	}

	public void createInfoObject(InfoObjectProperties infoObject) {
		final Sprite sprite = new Sprite(infoObject.getTexture());
		final Coordinate2d position = infoObject.getPosition();
		final Body sensor = bodyFactory.createInteractionSensor(
				(position.getX() + (sprite.getWidth() / 2)) / ConversionConstants.PIXELS_TO_METERS,
				(position.getY() + (sprite.getHeight() / 2)) / ConversionConstants.PIXELS_TO_METERS,
				(2 * sprite.getWidth()) / ConversionConstants.PIXELS_TO_METERS,
				(2 * sprite.getHeight()) / ConversionConstants.PIXELS_TO_METERS);

		final Dimension2d interactionBoxDimension = Dimension2d.of((2 * sprite.getWidth()), (2 * sprite.getHeight()));
		final int halfInteractionBoxWidth = interactionBoxDimension.getWidth() / 2;
		final int halfInteractionBoxHeight = interactionBoxDimension.getHeight() / 2;
		final Coordinate2d interactionBoxPosition = position.traverse(-halfInteractionBoxWidth,
				-halfInteractionBoxHeight);
		final InteractionTarget interactionTarget = new InteractionTarget(
				() -> Messages.publish(UiMessageCodes.DISPLAY_MESSAGE_BOX,
						UiMessageData.builder().withText(infoObject.getInfoText()).build()),
				InteractionBox.of(interactionBoxPosition, interactionBoxDimension));
		sensor.setUserData(interactionTarget);

		final Sprite icon = EntityIcons.INSPECT.get();
		icon.setPosition(position.getX(), position.getY() + icon.getHeight());
		final Entity entity = EntityBuilder.get().withSimpleSpriteProviderComponent(sprite)
				.withInteractionComponent(interactionTarget, icon)
				.withPositionComponent(new Vector3(position.getX(), position.getY(), 0))
				.withStateComponent(EntityStates.NONE).withDirectionalComponent(EntityDirections.NONE)
				.buildWithEngine(entityEngine);
		entityEngine.addEntity(entity);

		Messages.publish(EntityMessageCodes.INFO_OBJECT_CREATED, interactionTarget);
	}

}
