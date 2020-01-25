package com.koenigkatze.asoulforasoul.messages.routing;

import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.LevelMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MusicMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.SoundMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.UiMessageCodes;

import com.badlogic.gdx.ai.msg.Telegram;

public abstract class MessageRouterAdapter implements MessageRouter
{

	@Override
	public final boolean routeMessage(Telegram message)
	{
		final int messageCode = message.message;
		if (GameMessageCodes.isInRange(messageCode))
		{
			return handleGameMessage(message);
		}
		else if (LevelMessageCodes.isInRange(messageCode))
		{
			return handleLevelMessage(message);
		}
		else if (SoundMessageCodes.isInRange(messageCode))
		{
			return handleSoundMessage(message);
		}
		else if (EntityMessageCodes.isInRange(messageCode))
		{
			return handleEntityMessage(message);
		} 
		else if (MusicMessageCodes.isInRange(messageCode))
		{
			return handleMusicMessage(message);
		} 
		else if (UiMessageCodes.isInRange(messageCode))
		{
			return handleUiMessage(message);
		} 
		return false;
	}

	@Override
	public boolean handleGameMessage(Telegram message)
	{
		return false;
	}

	@Override
	public boolean handleLevelMessage(Telegram message)
	{
		return false;
	}

	@Override
	public boolean handleEntityMessage(Telegram message)
	{
		return false;
	}

	@Override
	public boolean handleSoundMessage(Telegram message)
	{
		return false;
	}
	
	@Override
	public boolean handleMusicMessage(Telegram message)
	{
		return false;
	}
	
	@Override
	public boolean handleUiMessage(Telegram message)
	{
		return false;
	}

}
