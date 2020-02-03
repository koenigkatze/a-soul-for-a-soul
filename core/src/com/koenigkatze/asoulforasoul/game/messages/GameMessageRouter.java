package com.koenigkatze.asoulforasoul.game.messages;

import com.koenigkatze.asoulforasoul.entities.interaction.InteractionTarget;
import com.koenigkatze.asoulforasoul.game.cursors.CursorManager;
import com.koenigkatze.asoulforasoul.game.screens.ScreenManager;
import com.koenigkatze.asoulforasoul.game.screens.Screens;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouterAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ai.msg.Telegram;

public final class GameMessageRouter extends MessageRouterAdapter
{
	private final ScreenManager screenManager;
	private final CursorManager cursorManager;

	public GameMessageRouter(final Game game)
	{
		screenManager = new ScreenManager(game);
		cursorManager = new CursorManager();
	}

	@Override
	public boolean handleGameMessage(final Telegram message)
	{
		final GameMessageCodes submittedMessageCode = GameMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		switch (submittedMessageCode)
		{
		case LOAD_GAME:
			screenManager.setScreen(Screens.START);
			break;
		case CHANGE_SCREEN:
			if (!(payload instanceof ScreenMessageData))
			{
				Logging.logError(GameMessageRouter.class,
						"Parsing message failed. Payload not instance of ScreenMessageData: " + payload);
				return false;
			}
			final ScreenMessageData messageData = (ScreenMessageData) message.extraInfo;
			screenManager.setScreen(messageData.getScreen());
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
		case MOUSE_OVER_RELEASED:
			cursorManager.handleReleasedMessage();
			return true;
		case MOUSE_OVER_INFO_OBJECT:
			if (!(payload instanceof InteractionTarget))
			{
				Logging.logError(GameMessageRouter.class,
						"Parsing message failed. Payload not instance of InteractionTarget: " + payload);
				return false;
			}
			final InteractionTarget messageData = (InteractionTarget) message.extraInfo;
			cursorManager.handleInfoObjectMessage(messageData);
			return true;
		default:
			break;
		}
		return false;
	}

}
