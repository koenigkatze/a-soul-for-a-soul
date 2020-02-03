package com.koenigkatze.asoulforasoul.level;

import java.util.ArrayList;
import java.util.List;

import com.koenigkatze.asoulforasoul.entities.interaction.InteractionTarget;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouterAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.msg.Telegram;

public final class LevelMessageRouter extends MessageRouterAdapter
{
	private final InputProcessor inputController;
	private final List<InteractionTarget> sensors;

	public LevelMessageRouter(final InputProcessor inputController)
	{
		super();
		this.inputController = inputController;
		sensors = new ArrayList<>();
	}

	@Override
	public boolean handleGameMessage(final Telegram message)
	{
		final GameMessageCodes submittedMessageCode = GameMessageCodes.getForCode(message.message);
		switch (submittedMessageCode)
		{
		case INPUT_UNLOCKED:
			Gdx.input.setInputProcessor(inputController);
			Logging.logDebug(LevelMessageRouter.class, "Input controller reset.");
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean handleEntityMessage(Telegram message)
	{
		final EntityMessageCodes submittedMessageCode = EntityMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		switch (submittedMessageCode)
		{
		case PLAYER_ENTITY_CREATED:
		case NPC_ENTITY_CREATED:
		case PASSIVE_MAP_OBJECT_CREATED:
			break;
		case INFO_OBJECT_CREATED:
			if (!(payload instanceof InteractionTarget))
			{
				Logging.logError(LevelMessageRouter.class,
						"Parsing message failed. Payload not instance of InteractionTarget: " + payload);
				return false;
			}
			final InteractionTarget target = (InteractionTarget) message.extraInfo;
			sensors.add(target);
			return true;
		case PLAYER_INTERACT:
			for (final InteractionTarget singleTarget : sensors) {
				if (singleTarget.isInteractable()) {
					singleTarget.interact();
					// TODO: Mal gucken, ob der early return auf lange Sicht ne gute Idee ist - eigentlich eher nicht.
					return true;
				}
			}
			break;
		default:
			Logging.logError(LevelMessageRouter.class, "Unknown message code. Message ignored: " + message.message);
			break;
		}
		return false;
	}
}
