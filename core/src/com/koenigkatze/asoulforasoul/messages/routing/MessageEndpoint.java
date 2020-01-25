package com.koenigkatze.asoulforasoul.messages.routing;

import java.util.Collection;

import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;

import com.badlogic.gdx.ai.msg.Telegraph;

public interface MessageEndpoint extends Telegraph
{
	Collection<MessageCode> getCodesToSuscribe();
}
