package com.koenigkatze.asoulforasoul.entities.messages;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.koenigkatze.asoulforasoul.level.LevelData;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.maps.entities.MapEntityFactory;
import com.koenigkatze.asoulforasoul.maps.general.BlockedObjectBulkProperties;
import com.koenigkatze.asoulforasoul.maps.general.BlockedObjectProperties;
import com.koenigkatze.asoulforasoul.maps.properties.character.CharacterMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.info.InfoObjectProperties;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.LevelMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouterAdapter;

public final class EntityMessageRouter extends MessageRouterAdapter
{
	private MapEntityFactory entityFactory;

	@Override
	public boolean handleEntityMessage(final Telegram message)
	{
		final EntityMessageCodes submittedMessageCode = EntityMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		if (entityFactory == null)
		{
			Logging.logError(EntityMessageRouter.class,
					"Entity factory not set up. Entity could not be created: " + payload);
			return false;
		}

		switch (submittedMessageCode)
		{
		case CREATE_BLOCKED_OBJECT:
			if (!(payload instanceof BlockedObjectProperties)) {
				Logging.logError(EntityMessageRouter.class,
						"Creating blocked object failed. Payload not instance of BlockedObjectProperties: " + payload);
				return false;
			}
			final BlockedObjectProperties blockedObjectProperties = (BlockedObjectProperties) payload;
			entityFactory.createBlockedObject(blockedObjectProperties);
			Logging.logDebug(EntityMessageRouter.class, "Blocked object created: " + blockedObjectProperties);
			return true;
		case CREATE_PLAYER_ENTITY:
			if (!(payload instanceof CharacterMapProperties))
			{
				Logging.logError(EntityMessageRouter.class,
						"Creating player entity failed. Payload not instance of CharacterMapProperties: " + payload);
				return false;
			}

			final CharacterMapProperties playerProperties = (CharacterMapProperties) payload;
			entityFactory.createPlayerEntity(playerProperties);
			Logging.logDebug(EntityMessageRouter.class, "Player entity created: " + playerProperties);
			return true;
		case CREATE_NPC_ENTITY:
			if (!(payload instanceof CharacterMapProperties))
			{
				Logging.logError(EntityMessageRouter.class,
						"Creating npc entity failed. Payload not instance of CharacterMapProperties: " + payload);
				return false;
			}

			final CharacterMapProperties npcProperties = (CharacterMapProperties) payload;
			entityFactory.createNpcEntity(npcProperties);
			Logging.logDebug(EntityMessageRouter.class, "Npc entity created: " + npcProperties);
			return true;
		case CREATE_PASSIVE_MAP_OBJECT:
			if (!(payload instanceof TiledMapTileMapObject))
			{
				Logging.logError(EntityMessageRouter.class,
						"Creating passive map entity failed. Payload not instance of TiledMapTileMapObject: "
								+ payload);
				return false;
			}

			final TiledMapTileMapObject passiveObject = (TiledMapTileMapObject) payload;
			entityFactory.createPassiveMapEntity(passiveObject);
			Logging.logDebug(EntityMessageRouter.class, "Passive map entity created: " + passiveObject);
			return true;
		case CREATE_INFO_OBJECT:
			if (!(payload instanceof InfoObjectProperties))
			{
				Logging.logError(EntityMessageRouter.class,
						"Creating active map object failed. Payload not instance of InfoObjectProperties: "
								+ payload);
				return false;
			}

			final InfoObjectProperties infoObject = (InfoObjectProperties) payload;
			entityFactory.createInfoObject(infoObject);
			Logging.logDebug(EntityMessageRouter.class, "Info object created: " + infoObject);
			return true;
		case BULK_CREATE_BLOCKED_OBJECT:
			if (!(payload instanceof BlockedObjectBulkProperties)) {
				Logging.logError(EntityMessageRouter.class,
						"Bulk creating blocked objects failed. Payload not instance of BlockedObjectBulkProperties: " + payload);
				return false;
			}
			final BlockedObjectBulkProperties bulkProperties = (BlockedObjectBulkProperties) payload;
			entityFactory.bulkCreateBlockedObject(bulkProperties);
			Logging.logDebug(EntityMessageRouter.class, "Bulk created blocked objects: " + bulkProperties);
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean handleLevelMessage(final Telegram message)
	{
		final LevelMessageCodes submittedMessageCode = LevelMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		switch (submittedMessageCode)
		{
		case LEVEL_DATA_LOADED:
			if (!(payload instanceof LevelData))
			{
				Logging.logError(EntityMessageRouter.class,
						"Setting up entity factory failed. LevelData corrupted: " + payload);
				return false;
			}
			final LevelData levelData = (LevelData) payload;
			entityFactory = new MapEntityFactory(levelData.getEntityEngine(), levelData.getWorld());
			break;
		default:
			Logging.logError(EntityMessageRouter.class, "Unknown message code. Message ignored: " + message.message);
			break;
		}
		return false;
	}
}
