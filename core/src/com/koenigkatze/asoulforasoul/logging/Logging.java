package com.koenigkatze.asoulforasoul.logging;

import com.badlogic.gdx.Gdx;

public final class Logging
{
	private Logging() {
		throw new UnsupportedOperationException("This constructor is private!");
	}

	public static void logError(final String tag, final String message)
	{
		Gdx.app.error(tag, message);
	}

	public static void logError(final Class<?> clazz, final String message)
	{
		Gdx.app.error(clazz.getSimpleName(), message);
	}

	public static void logDebug(final Class<?> clazz, final String message)
	{
		Gdx.app.debug(clazz.getSimpleName(), message);
	}

}
