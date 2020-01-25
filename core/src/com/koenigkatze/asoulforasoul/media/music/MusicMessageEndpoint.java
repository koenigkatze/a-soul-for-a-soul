package com.koenigkatze.asoulforasoul.media.music;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouter;

import com.badlogic.gdx.ai.msg.Telegram;

public class MusicMessageEndpoint implements MessageEndpoint
{
	private final MessageRouter router;
	private final List<MessageCode> codesToSuscribe;

	public MusicMessageEndpoint()
	{
		super();
		router = new MusicMessageRouter();
		codesToSuscribe = Collections.emptyList();
	}

	@Override
	public Collection<MessageCode> getCodesToSuscribe()
	{
		return Collections.unmodifiableList(codesToSuscribe);
	}

	@Override
	public boolean handleMessage(Telegram message)
	{
		return router.routeMessage(message);
	}

}
