package com.koenigkatze.asoulforasoul.game.screens;

import com.koenigkatze.asoulforasoul.level.Level;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;
import com.koenigkatze.asoulforasoul.messages.codes.UiMessageCodes;
import com.koenigkatze.asoulforasoul.messages.routing.MessageEndpoint;
import com.koenigkatze.asoulforasoul.ui.UiManager;
import com.koenigkatze.asoulforasoul.ui.UiMessageEndpoint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class GameScreen extends ScreenAdapter
{
	private Level level;
	private UiManager uiManager;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void show()
	{
		level = new Level();
		level.load();

		uiManager = new UiManager();
		registerUiEndpoint();
		
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	private void registerUiEndpoint()
	{
		final MessageManager messageManager = MessageManager.getInstance();
		final MessageEndpoint uiMessageEndpoint = new UiMessageEndpoint(uiManager);
		for (final MessageCode uiCodes : uiMessageEndpoint.getCodesToSuscribe())
		{
			messageManager.addListener(uiMessageEndpoint, uiCodes.get());
		}
		for (final UiMessageCodes uiCode : UiMessageCodes.values())
		{
			messageManager.addListener(uiMessageEndpoint, uiCode.get());
		}
		Logging.logDebug(GameScreen.class, "Ui message endpoint registered.");
	}

	@Override
	public void render(final float delta)
	{
		GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
		MessageManager.getInstance().update();
		super.render(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		level.update();

		uiManager.render(delta);
		
		batch.begin();
		font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), Gdx.graphics.getWidth()-20, Gdx.graphics.getHeight()-10);
		batch.end();
	}

	@Override
	public void resize(final int width, final int height)
	{
		super.resize(width, height);
		level.resize();
	}

	@Override
	public void dispose()
	{
		super.dispose();
		level.dispose();
	}

}
