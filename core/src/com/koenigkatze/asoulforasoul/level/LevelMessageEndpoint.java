package com.koenigkatze.asoulforasoul.level;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouter;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.msg.Telegram;

public final class LevelMessageEndpoint implements MessageEndpoint
{
	private final MessageRouter router;
	private final List<MessageCode> codesToSuscribe;

	public LevelMessageEndpoint(final InputProcessor inputController)
	{
		super();
		router = new LevelMessageRouter(inputController);
		codesToSuscribe = Arrays.asList(GameMessageCodes.INPUT_UNLOCKED, EntityMessageCodes.INFO_OBJECT_CREATED, EntityMessageCodes.PLAYER_INTERACT);
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
