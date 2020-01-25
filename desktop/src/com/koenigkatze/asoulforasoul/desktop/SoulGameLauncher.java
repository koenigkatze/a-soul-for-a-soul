package com.koenigkatze.asoulforasoul.desktop;

import com.koenigkatze.asoulforasoul.game.SoulGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.utils.Logger;

public class SoulGameLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 768;
//		config.fullscreen = true;
		final LwjglApplication lwjglApplication = new LwjglApplication(new SoulGame(), config);
		lwjglApplication.setLogLevel(Logger.DEBUG);
	}
}
