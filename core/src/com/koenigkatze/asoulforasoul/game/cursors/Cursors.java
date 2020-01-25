package com.koenigkatze.asoulforasoul.game.cursors;

import com.koenigkatze.asoulforasoul.constants.FileConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;

public enum Cursors
{
	STANDARD("standard.png"), 
	INSPECT("inspect.png"),
	INSPECT_PALE("inspect_pale.png");

	private final Cursor cursor;

	Cursors(final String path)
	{
		final Pixmap pixmap = new Pixmap(Gdx.files.internal(FileConstants.CURSORS_STANDARD_PATH + path));
		cursor = Gdx.graphics.newCursor(pixmap, 0, 0);
		pixmap.dispose();
	}
	
	public Cursor get() {
		return cursor;
	}
}
