package com.koenigkatze.asoulforasoul.game;

import com.badlogic.gdx.Game;
import com.koenigkatze.asoulforasoul.game.setup.BasicMessagingSetup;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.MessageBuilder;

public final class SoulGame extends Game
{
	@Override
	public void create()
	{
		BasicMessagingSetup.registerBasicEndpoints(this);
		MessageBuilder.forType(GameMessageCodes.LOAD_GAME.get()).dispatch();
	}

	@Override
	public void render()
	{
		super.render();
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

}
