package com.koenigkatze.asoulforasoul.entities.messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.koenigkatze.asoulforasoul.messages.codes.LevelMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouter;

import com.badlogic.gdx.ai.msg.Telegram;

public final class EntityMessageEndpoint implements MessageEndpoint
{
	private final MessageRouter router;
	private final List<MessageCode> codesToSuscribe;

	public EntityMessageEndpoint()
	{
		super();
		router = new EntityMessageRouter();
		codesToSuscribe = new ArrayList<>(Arrays.asList(LevelMessageCodes.LEVEL_DATA_LOADED));
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
