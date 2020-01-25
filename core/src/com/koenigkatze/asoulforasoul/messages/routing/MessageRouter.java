package com.koenigkatze.asoulforasoul.messages.routing;

import com.badlogic.gdx.ai.msg.Telegram;

public interface MessageRouter
{

	public boolean routeMessage(Telegram message);
	
	public boolean handleGameMessage(Telegram message);

	public boolean handleLevelMessage(Telegram message);
	
	public boolean handleEntityMessage(Telegram message);
	
	public boolean handleSoundMessage(Telegram message);

	public boolean handleMusicMessage(Telegram message);

	public boolean handleUiMessage(Telegram message);
}
