package com.koenigkatze.asoulforasoul.game.screens;

import com.koenigkatze.asoulforasoul.game.messages.ScreenMessageData;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.MessageBuilder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public final class MenuScreen extends ScreenAdapter
{
	private final Sprite backgroundSprite;
	private final Stage stage;

	public MenuScreen()
	{
		super();
		backgroundSprite = new Sprite(new Texture(Gdx.files.internal("images/backgrounds/page.png")));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show()
	{
		super.show();
		Logging.logDebug(MenuScreen.class, "MenuScreen active. Painting menu...");
		
		final Table table = new Table();
		table.setBackground(new SpriteDrawable(backgroundSprite));
		table.setFillParent(true);
		table.setDebug(true);
		stage.addActor(table);

		final Skin skin = new Skin(Gdx.files.internal("skin/glassy/glassy-ui.json"));
		final TextButton newGame = new TextButton("New Game", skin);
		newGame.addListener(new ChangeListener()
		{
			@Override
			public void changed(final ChangeEvent event, final Actor actor)
			{
				Logging.logDebug(MenuScreen.class, "Changing to game screen.");
				MessageBuilder.forType(GameMessageCodes.CHANGE_SCREEN.get())
						.withExtraInfo(new ScreenMessageData(Screens.GAME)).dispatch();
			}
		});

		final TextButton settings = new TextButton("Settings", skin);
		settings.addListener(new ChangeListener()
		{
			@Override
			public void changed(final ChangeEvent event, final Actor actor)
			{
				Logging.logDebug(MenuScreen.class, "Changing to settings screen.");
				MessageBuilder.forType(GameMessageCodes.CHANGE_SCREEN.get())
						.withExtraInfo(new ScreenMessageData(Screens.SETTINGS)).dispatch();
			}
		});

		final TextButton exit = new TextButton("Exit", skin);
		exit.addListener(new ChangeListener()
		{
			@Override
			public void changed(final ChangeEvent event, final Actor actor)
			{
				Logging.logDebug(MenuScreen.class, "Exiting the game...");
				Gdx.app.exit();
			}
		});

		table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(settings).fillX().uniformX();
		table.row();
		table.add(exit).fillX().uniformX();

		Logging.logDebug(MenuScreen.class, "Menu painting complete.");
	}

	@Override
	public void resize(final int width, final int height)
	{
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(final float delta)
	{
		super.render(delta);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}
