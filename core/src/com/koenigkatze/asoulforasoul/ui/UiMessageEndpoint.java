package com.koenigkatze.asoulforasoul.ui;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.ai.msg.Telegram;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.messages.routing.MessageRouter;

public final class UiMessageEndpoint implements MessageEndpoint
{
	private final MessageRouter router;
	private final List<MessageCode> codesToSuscribe;

	public UiMessageEndpoint(final MessageRouter messageRouter)
	{
		super();
		router = messageRouter;
		codesToSuscribe = Collections.emptyList();
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
