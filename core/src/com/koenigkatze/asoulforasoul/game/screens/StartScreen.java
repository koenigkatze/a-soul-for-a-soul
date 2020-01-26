package com.koenigkatze.asoulforasoul.game.screens;

import com.koenigkatze.asoulforasoul.game.cursors.Cursors;
import com.koenigkatze.asoulforasoul.game.messages.ScreenMessageData;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.media.animations.AnimationResourcesLoader;
import com.koenigkatze.asoulforasoul.media.music.MusicResourcesLoader;
import com.koenigkatze.asoulforasoul.media.sound.SoundResourcesLoader;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.MessageBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;

public final class StartScreen extends ScreenAdapter
{
	private AssetManager assetManager;

	@Override
	public void show()
	{
		super.show();

		Logging.logDebug(StartScreen.class, "StartScreen active. Starting to load the game...");
		loadAssets();
		
		Gdx.graphics.setTitle("Grim Reaper: A Soul For A Soul");
		Gdx.graphics.setCursor(Cursors.STANDARD.get());
		
		Logging.logDebug(StartScreen.class, "Game loading completed. Changing to menu screen.");
//		MessageBuilder.forType(GameMessageCodes.CHANGE_SCREEN.get()).withExtraInfo(new ScreenMessageData(Screens.MENU))
//				.dispatch();
		MessageBuilder.forType(GameMessageCodes.CHANGE_SCREEN.get()).withExtraInfo(new ScreenMessageData(Screens.GAME))
		.dispatch();
	}

	private void loadAssets()
	{
		Logging.logDebug(StartScreen.class, "Loading assets...");
		Logging.logDebug(StartScreen.class, "Loading animations...");
		assetManager = new AssetManager();
		final AnimationResourcesLoader animationLoader = new AnimationResourcesLoader(assetManager);
		animationLoader.loadAtlantes();
		animationLoader.loadAnimations();
		Logging.logDebug(StartScreen.class, "Loading animations complete.");

		Logging.logDebug(StartScreen.class, "Loading music...");
		final MusicResourcesLoader musicLoader = new MusicResourcesLoader(assetManager);
		musicLoader.loadMusic();
		Logging.logDebug(StartScreen.class, "Loading music complete.");

		Logging.logDebug(StartScreen.class, "Loading sounds...");
		final SoundResourcesLoader soundLoader = new SoundResourcesLoader(assetManager);
		soundLoader.loadSounds();
		Logging.logDebug(StartScreen.class, "Loading sounds complete.");

		Logging.logDebug(StartScreen.class, "Loading assets complete.");
	}

	@Override
	public void dispose()
	{
		super.dispose();
		assetManager.dispose();
	}

}
