package com.koenigkatze.asoulforasoul.ui;

import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.UiMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouterAdapter;

import com.badlogic.gdx.ai.msg.Telegram;

public final class UiMessageRouter extends MessageRouterAdapter
{
	private final UiManager uiManager;

	public UiMessageRouter(final UiManager uiManager)
	{
		super();
		this.uiManager = uiManager;
	}

	@Override
	public boolean handleUiMessage(final Telegram message)
	{
		final UiMessageCodes submittedMessageCode = UiMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		switch (submittedMessageCode)
		{
		case DISPLAY_MESSAGE_BOX:
			if (!(payload instanceof UiMessageData))
			{
				Logging.logError(UiMessageRouter.class,
						"Parsing message failed. Payload not instance of UiMessageData: " + payload);
				return false;
			}
			final UiMessageData messageData = (UiMessageData) message.extraInfo;
			uiManager.displayMessageBox(messageData);
			return true;
		default:
			Logging.logError(UiMessageRouter.class, "Unknown message code. Message ignored: " + message.message);
			break;
		}
		return false;
	}

}
