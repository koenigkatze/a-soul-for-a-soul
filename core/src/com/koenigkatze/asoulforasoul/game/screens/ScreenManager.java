package com.koenigkatze.asoulforasoul.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public final class ScreenManager
{
	private final Game game;
	private Screen startScreen;
	private Screen menuScreen;
	private Screen preferencesScreen;
	private Screen gameScreen;

	public ScreenManager(final Game game)
	{
		this.game = game;
	}

	public void setScreen(final Screens value)
	{
		switch (value)
		{
		case START:
			if (startScreen == null)
			{
				startScreen = new StartScreen();
			}
			game.setScreen(startScreen);
			break;
		case MENU:
			if (menuScreen == null)
			{
				menuScreen = new MenuScreen();
			}
			game.setScreen(menuScreen);
			break;
		case SETTINGS:
			if (preferencesScreen == null)
			{
				preferencesScreen = new SettingsScreen();
			}
			game.setScreen(preferencesScreen);
			break;
		case GAME:
			if (gameScreen == null)
			{
				gameScreen = new GameScreen();
			}
			game.setScreen(gameScreen);
			break;
		default:
			break;
		}
	}

}
