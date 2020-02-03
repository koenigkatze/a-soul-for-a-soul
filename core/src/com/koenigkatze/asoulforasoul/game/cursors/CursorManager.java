package com.koenigkatze.asoulforasoul.game.cursors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.koenigkatze.asoulforasoul.entities.interaction.InteractionTarget;

public class CursorManager
{
	private Cursor currentCursor;
	
	public CursorManager()
	{
		super();
//		this.currentCursor = Cursors.STANDARD.get();
	}

	public void handleReleasedMessage()
	{
		currentCursor = Cursors.STANDARD.get();
		Gdx.graphics.setCursor(currentCursor);
	}

	public void handleInfoObjectMessage(InteractionTarget target)
	{
		changeCursorForInfoObject(target.isInteractable());
		target.addListener(this::changeCursorForInfoObject);
	}

	private void changeCursorForInfoObject(boolean interactable)
	{
		if (interactable) {
			currentCursor = Cursors.INSPECT.get();
			Gdx.graphics.setCursor(currentCursor);
			return;
		}
		currentCursor = Cursors.INSPECT_PALE.get();
		Gdx.graphics.setCursor(currentCursor);
	}

}
