package com.koenigkatze.asoulforasoul.game.messages;

import com.koenigkatze.asoulforasoul.game.screens.Screens;

public final class ScreenMessageData
{
	private final Screens screen;

	public ScreenMessageData(final Screens screen)
	{
		super();
		this.screen = screen;
	}

	public Screens getScreen()
	{
		return screen;
	}

}
