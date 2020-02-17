package com.koenigkatze.asoulforasoul.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.koenigkatze.asoulforasoul.entities.EntityManager;
import com.koenigkatze.asoulforasoul.level.Level;
import com.koenigkatze.asoulforasoul.ui.UiManager;

public final class GameScreen extends ScreenAdapter {
	private Level level;
	private UiManager uiManager;
	private SpriteBatch batch;
	private BitmapFont font;
	private EntityManager entityManager;

	@Override
	public void show() {
		entityManager = new EntityManager();

		uiManager = new UiManager();

		batch = new SpriteBatch();
		font = new BitmapFont();

		level = new Level();
		level.load("test_2");
	}

	@Override
	public void render(final float delta) {
		GdxAI.getTimepiece().update(Gdx.graphics.getDeltaTime());
		MessageManager.getInstance().update();
		super.render(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		entityManager.update(delta);

		uiManager.render(delta);

		batch.begin();
		font.draw(batch, String.valueOf(Gdx.graphics.getFramesPerSecond()), Gdx.graphics.getWidth() - 20,
				Gdx.graphics.getHeight() - 10);
		batch.end();
	}

	@Override
	public void resize(final int width, final int height) {
		super.resize(width, height);
		level.resize();
	}

	@Override
	public void dispose() {
		super.dispose();
		level.dispose();
	}

}
