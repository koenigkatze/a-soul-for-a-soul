package com.koenigkatze.asoulforasoul.ui;

import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public final class Widgets
{
	private final static Skin SKIN = new Skin(Gdx.files.internal("skin/glassy/glassy-ui.json"));

	public static Actor getMessageBox(final String text)
	{
		final TextField messageBox = new TextField(text, SKIN);
		final int width = Gdx.graphics.getWidth() / 3;
		messageBox.setWidth(width);
		final int height = Gdx.graphics.getHeight() / 5;
		messageBox.setHeight(height);
		messageBox.setAlignment(Align.center);
		final int x = (Gdx.graphics.getWidth() / 2) - (width / 2);
		final int y = (Gdx.graphics.getHeight() / 2) - (height / 2);
		messageBox.setPosition(x, y);

		messageBox.setDisabled(true);
		
		messageBox.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				Logging.logDebug(UiManager.class, "Message box clicked. Input was unlocked.");
				messageBox.remove();
				Messages.publish(GameMessageCodes.INPUT_UNLOCKED);
			}
		});
		return messageBox;
	}

}
