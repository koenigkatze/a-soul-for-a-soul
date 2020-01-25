package com.koenigkatze.asoulforasoul.ui;

import com.koenigkatze.asoulforasoul.constants.FileConstants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum EntityIcons
{
	INSPECT("inspect.png");

	private final String path;
	private Sprite icon;

	EntityIcons(final String path) {
		this.path = FileConstants.ICONS_STANDARD_PATH + path;
	}
	
	public Sprite get()
	{
		if (icon != null) {
			return icon;
		}
		
		return icon = new Sprite(new Texture(Gdx.files.internal(path)));
	}

}
