package com.koenigkatze.asoulforasoul.media.music;

import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.media.exceptions.UnknownMediaException;
import com.koenigkatze.asoulforasoul.messages.codes.MusicMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouterAdapter;

import com.badlogic.gdx.ai.msg.Telegram;

public class MusicMessageRouter extends MessageRouterAdapter
{
	private final MusicController controller;
	
	public MusicMessageRouter()
	{
		super();
		controller = new MusicController();
	}

	@Override
	public boolean handleMusicMessage(final Telegram message)
	{
		final MusicMessageCodes submittedMessageCode = MusicMessageCodes.getForCode(message.message);
		final Object payload = message.extraInfo;
		switch (submittedMessageCode) {
		case DECREASE_VOLUME:
			break;
		case INCREASE_VOLUME:
			break;
		case MUTE:
			break;
		case PLAY:
			if (!(payload instanceof MusicMessageData)) {
				Logging.logError(MusicMessageRouter.class,
						"Parsing message failed. Payload not instance of MusicMessageData: "+payload);
				return false;
			}
			final MusicMessageData messageData = (MusicMessageData) message.extraInfo;
			try {
				controller.play(messageData.getName(), messageData.isLooped());
			} catch (final UnknownMediaException ume) {
				Logging.logError(MusicMessageRouter.class, "Unable to play music track. File not found: "+messageData.getName());
				return false;
			}
			return true;
		case STOP:
			break;
		case TOGGLE_MUTE_UNMUTE:
			controller.toggleMute();
			return true;
		default:
			Logging.logError(MusicMessageRouter.class,
					"Unknown message code. Message ignored: " + message.message);
			break;
		}
		return false;
	}
	
}
