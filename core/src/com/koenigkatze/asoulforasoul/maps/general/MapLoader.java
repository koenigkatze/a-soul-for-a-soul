package com.koenigkatze.asoulforasoul.maps.general;

import java.util.Collection;

import com.koenigkatze.asoulforasoul.constants.ConversionConstants;
import com.koenigkatze.asoulforasoul.constants.FileConstants;
import com.koenigkatze.asoulforasoul.entities.physics.BodyFactory;
import com.koenigkatze.asoulforasoul.maps.MapObjectLayers;
import com.koenigkatze.asoulforasoul.maps.entities.MapEntityTypes;
import com.koenigkatze.asoulforasoul.maps.exceptions.MapObjectMissingAttributeException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapObjectTypeException;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapPropertiesMapper;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapPropertiesMapper;
import com.koenigkatze.asoulforasoul.maps.properties.general.GeneralMapObjectProperties;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapObjectType;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectProperties;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public final class MapLoader
{
	private final CharacterMapPropertiesMapper characterMapper;
	
	private TiledMap tiledMap;
	private MapAccess mapAccess;
	private CustomMapPropertiesMapper mapPropertiesMapper;
	
	public MapLoader()
	{
		super();
		 characterMapper = new CharacterMapPropertiesMapper();
	}

	public TiledMap loadFromStandardPath(final String name)
	{
		tiledMap = new TmxMapLoader().load(FileConstants.MAPS_STANDARD_PATH + name);
		mapAccess = new MapAccess(tiledMap);
		mapPropertiesMapper = new CustomMapPropertiesMapper();

		return tiledMap;
	}

	public CustomMapProperties loadMapProperties()
	{
		final MapProperties properties = tiledMap.getProperties();
		return mapPropertiesMapper.loadProperties(properties);
	}

	public TiledMap getMap()
	{
		if (tiledMap == null)
		{
			throw new IllegalStateException("There is no current map loaded.");
		}
		return tiledMap;
	}

	public void loadBlockedMapParts(final BodyFactory bodyFactory)
	{
		final Collection<RectangleMapObject> blockedMapObjects = mapAccess
				.getRectangleMapObjectsForLayer(MapObjectLayers.BLOCKED.getName());

		for (final RectangleMapObject singleMapObject : blockedMapObjects)
		{
			final Rectangle rectangle = singleMapObject.getRectangle();
			bodyFactory.createRectangleBody(
					(rectangle.x + (rectangle.width / 2)) / ConversionConstants.PIXELS_TO_METERS,
					(rectangle.y + (rectangle.height / 2)) / ConversionConstants.PIXELS_TO_METERS,
					rectangle.width / ConversionConstants.PIXELS_TO_METERS,
					rectangle.height / ConversionConstants.PIXELS_TO_METERS, BodyType.StaticBody);
		}
	}

	public void loadCharacterEntitiesFromMap()
	{
		final Collection<MapObject> mapObjects = mapAccess.getAllObjectsForLayer(MapObjectLayers.POSITIONS.getName());
		for (final MapObject singleObject : mapObjects)
		{
			final MapProperties properties = singleObject.getProperties();
			if (!properties.containsKey(GeneralMapObjectProperties.TYPE.getTag()))
			{
				continue;
			}
			final MapEntityTypes objectsType = MapEntityTypes.valueOf(((String) properties.get(GeneralMapObjectProperties.TYPE.getTag())).toUpperCase());
			switch (objectsType)
			{
			case PLAYER:
				final CharacterMapProperties playerProperties = characterMapper.loadProperties(properties);
				Messages.publish(EntityMessageCodes.CREATE_PLAYER_ENTITY, playerProperties);
				break;
			case NPC:
				final CharacterMapProperties npcProperties = characterMapper.loadProperties(properties);
				Messages.publish(EntityMessageCodes.CREATE_NPC_ENTITY, npcProperties);
				break;
			default:
				throw new UnknownMapObjectTypeException();
			}
		}
	}

	public void loadPassiveObjects()
	{
		final Collection<MapObject> mapObjects = mapAccess
				.getAllObjectsForLayer(MapObjectLayers.PASSIVE_MAP_OBJECTS.getName());
		for (final MapObject singleObject : mapObjects)
		{
			if (singleObject instanceof TiledMapTileMapObject)
			{
				final TiledMapTileMapObject mapObject = (TiledMapTileMapObject) singleObject;
				Messages.publish(EntityMessageCodes.CREATE_PASSIVE_MAP_OBJECT, mapObject);
			}
		}
	}

	public void loadActiveMapObjects()
	{
		final Collection<MapObject> mapObjects = mapAccess
				.getAllObjectsForLayer(MapObjectLayers.ACTIVE_MAP_OBJECTS.getName());
		for (final MapObject singleObject : mapObjects)
		{
			if (singleObject instanceof TiledMapTileMapObject)
			{
				final TiledMapTileMapObject mapObject = (TiledMapTileMapObject) singleObject;
				final MapProperties properties = mapObject.getProperties();
				final Object typeRaw = properties.get(GeneralMapObjectProperties.TYPE.getTag());
				if (!(typeRaw instanceof String)) {
					throw new MapObjectMissingAttributeException();
				}
				final String typeName = (String) typeRaw;
				final MapObjectType objectType = MapObjectType.valueOf(typeName.toUpperCase());
				switch (objectType) {
				case INFO_OBJECT:
					final Object infoTextRaw = properties.get(InfoObjectMapProperties.INFOTEXT.getTag());
					if (!(infoTextRaw instanceof String)) {
						throw new MapObjectMissingAttributeException();
					}
					final String infoText = (String) infoTextRaw;
					Messages.publish(EntityMessageCodes.CREATE_INFO_OBJECT, new InfoObjectProperties(
							Coordinate2d.of(mapObject.getX(), mapObject.getY()),
							mapObject.getTextureRegion(),
							infoText));
					break;
				default:
					break;
				}
			}
		}
	}

}
