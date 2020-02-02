package com.koenigkatze.asoulforasoul.maps.general;

import java.util.Collection;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.koenigkatze.asoulforasoul.game.world.Coordinate2d;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.maps.MapObjectLayers;
import com.koenigkatze.asoulforasoul.maps.entities.MapEntityTypes;
import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapObjectTypeException;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapPropertiesMapper;
import com.koenigkatze.asoulforasoul.maps.properties.general.GeneralMapObjectProperties;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapObjectType;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectProperties;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

public final class MapObjectCreator {
	public static void createFromMap(final TiledMap tiledMap) {
		final MapAccess mapAccess = new MapAccess(tiledMap);

		createBlockedMapParts(mapAccess.getRectangleMapObjectsForLayer(MapObjectLayers.BLOCKED.getName()));
		createCharacterEntities(mapAccess.getAllObjectsForLayer(MapObjectLayers.ENTITY_STARTING_POSITIONS.getName()));
		createPassiveMapObjects(mapAccess.getAllObjectsForLayer(MapObjectLayers.PASSIVE_MAP_OBJECTS.getName()));
		createActiveMapObjects(mapAccess.getAllObjectsForLayer(MapObjectLayers.ACTIVE_MAP_OBJECTS.getName()));
	}

	private static void createBlockedMapParts(final Collection<RectangleMapObject> blockedMapObjects) {
		BlockedMapPartsCreator.createFromCollection(blockedMapObjects);
	}

	/*
	 * TODO: Muss überarbeitet werden! Hier können nicht nur MapEntities ankommen!
	 * Das ist der Positions-Layer!
	 */
	private static void createCharacterEntities(final Collection<MapObject> mapObjects) {
		Logging.logDebug(MapObjectCreator.class, "Loading character entities from map...");

		for (final MapObject singleObject : mapObjects) {
			final MapProperties properties = singleObject.getProperties();
			if (!properties.containsKey(GeneralMapObjectProperties.TYPE.getTag())) {
				continue;
			}
			final MapEntityTypes objectsType = MapEntityTypes
					.valueOf(((String) properties.get(GeneralMapObjectProperties.TYPE.getTag())).toUpperCase());
			switch (objectsType) {
			case PLAYER:
				final CharacterMapProperties playerProperties = CharacterMapPropertiesMapper.loadProperties(properties);
				Messages.publish(EntityMessageCodes.CREATE_PLAYER_ENTITY, playerProperties);
				break;
			case NPC:
				final CharacterMapProperties npcProperties = CharacterMapPropertiesMapper.loadProperties(properties);
				Messages.publish(EntityMessageCodes.CREATE_NPC_ENTITY, npcProperties);
				break;
			default:
				throw new UnknownMapObjectTypeException();
			}
		}
		Logging.logDebug(MapObjectCreator.class, "Loading character entities from map completed.");
	}

	private static void createPassiveMapObjects(Collection<MapObject> mapObjects) {
		for (final MapObject singleObject : mapObjects) {
			if (singleObject instanceof TiledMapTileMapObject) {
				final TiledMapTileMapObject mapObject = (TiledMapTileMapObject) singleObject;
				Messages.publish(EntityMessageCodes.CREATE_PASSIVE_MAP_OBJECT, mapObject);
			}
		}
	}

	private static void createActiveMapObjects(Collection<MapObject> mapObjects) {
		for (final MapObject singleObject : mapObjects) {
			if (singleObject instanceof TiledMapTileMapObject) {
				final TiledMapTileMapObject mapObject = (TiledMapTileMapObject) singleObject;
				final MapProperties properties = mapObject.getProperties();
				final Object typeRaw = properties.get(GeneralMapObjectProperties.TYPE.getTag());
				if (!(typeRaw instanceof String)) {
					throw new MissingMapPropertyException();
				}
				final String typeName = (String) typeRaw;
				final MapObjectType objectType = MapObjectType.valueOf(typeName.toUpperCase());
				switch (objectType) {
				case INFO_OBJECT:
					final Object infoTextRaw = properties.get(InfoObjectMapProperties.INFOTEXT.getTag());
					if (!(infoTextRaw instanceof String)) {
						throw new MissingMapPropertyException();
					}
					final String infoText = (String) infoTextRaw;
					Messages.publish(EntityMessageCodes.CREATE_INFO_OBJECT,
							InfoObjectProperties.of(Coordinate2d.of(mapObject.getX(), mapObject.getY()),
									mapObject.getTextureRegion(), infoText));
					break;
				default:
					break;
				}
			}
		}
	}

}
