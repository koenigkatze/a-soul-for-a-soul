package com.koenigkatze.asoulforasoul.game.setup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.koenigkatze.asoulforasoul.game.messages.GameMessageEndpoint;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.media.music.MusicMessageEndpoint;
import com.koenigkatze.asoulforasoul.media.sound.SoundMessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.codes.MusicMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.SoundMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;

public final class BasicMessagingSetup
{
	public static void registerBasicEndpoints(final Game game)
	{
		Logging.logDebug(BasicMessagingSetup.class, "Registering message endpoints...");
		final MessageManager messageManager = MessageManager.getInstance();
		messageManager.clear();

		registerGameEndpoint(messageManager, game);
		Logging.logDebug(BasicMessagingSetup.class, "Game message endpoint registered.");

		registerSoundEndpoint(messageManager);
		Logging.logDebug(BasicMessagingSetup.class, "Sound message endpoint registered.");

		registerMusicEndpoint(messageManager);
		Logging.logDebug(BasicMessagingSetup.class, "Music message endpoint registered.");

		Logging.logDebug(BasicMessagingSetup.class, "Registering message endpoints complete.");
	}

	private static void registerGameEndpoint(final MessageManager messageManager, final Game game)
	{
		final MessageEndpoint gameEndpoint = new GameMessageEndpoint(game);
		for (final GameMessageCodes gameCode : GameMessageCodes.values())
		{
			messageManager.addListener(gameEndpoint, gameCode.get());
		}
		for (final MessageCode entityCode : gameEndpoint.getCodesToSuscribe())
		{
			messageManager.addListener(gameEndpoint, entityCode.get());
		}
	}



	private static void registerSoundEndpoint(final MessageManager messageManager)
	{
		final MessageEndpoint soundMessageEndpoint = new SoundMessageEndpoint();
		for (final MessageCode soundCodes : soundMessageEndpoint.getCodesToSuscribe())
		{
			messageManager.addListener(soundMessageEndpoint, soundCodes.get());
		}
		for (final SoundMessageCodes soundCode : SoundMessageCodes.values())
		{
			messageManager.addListener(soundMessageEndpoint, soundCode.get());
		}
	}

	private static void registerMusicEndpoint(final MessageManager messageManager)
	{
		final MessageEndpoint musicMessageEndpoint = new MusicMessageEndpoint();
		for (final MessageCode musicCodes : musicMessageEndpoint.getCodesToSuscribe())
		{
			messageManager.addListener(musicMessageEndpoint, musicCodes.get());
		}
		for (final MusicMessageCodes musicCode : MusicMessageCodes.values())
		{
			messageManager.addListener(musicMessageEndpoint, musicCode.get());
		}
	}

}
