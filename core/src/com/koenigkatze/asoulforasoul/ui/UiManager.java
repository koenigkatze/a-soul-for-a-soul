package com.koenigkatze.asoulforasoul.ui;

import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.GameMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public final class UiManager
{
	private final Stage stage;
	private Actor currentActor;

	public UiManager()
	{
		super();
		stage = new Stage(new ScreenViewport());
		stage.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode)
			{
				if (keycode == Keys.E && currentActor != null) {
					currentActor.remove();
					Messages.publish(GameMessageCodes.INPUT_UNLOCKED);
				}
				return super.keyUp(event, keycode);
			}
		});
	}

	public void displayMessageBox(final UiMessageData messageData)
	{
		Logging.logDebug(UiManager.class, "Displaying message box: " + messageData);
		Logging.logDebug(UiManager.class, "Input will be locked.");

		currentActor = Widgets.getMessageBox(messageData.getText());
		stage.addActor(currentActor);
		
		Gdx.input.setInputProcessor(stage);
		Messages.publish(GameMessageCodes.INPUT_LOCKED);
	}

	public void render(final float delta)
	{
		stage.act(delta);
		stage.draw();
	}

}
