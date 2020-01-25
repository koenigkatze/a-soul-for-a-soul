package com.koenigkatze.asoulforasoul.game.messages;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ai.msg.Telegram;

public final class GameMessageEndpoint implements MessageEndpoint
{
	private final MessageRouter router;
	private final List<MessageCode> codesToSuscribe;

	public GameMessageEndpoint(final Game game)
	{
		super();
		router = new GameMessageRouter(game);
		codesToSuscribe = Arrays.asList(EntityMessageCodes.MOUSE_OVER_RELEASED, EntityMessageCodes.MOUSE_OVER_INFO_OBJECT);
	}

	@Override
	public Collection<MessageCode> getCodesToSuscribe()
	{
		return Collections.unmodifiableList(codesToSuscribe);
	}

	@Override
	public boolean handleMessage(final Telegram message)
	{
		return router.routeMessage(message);
	}

}
