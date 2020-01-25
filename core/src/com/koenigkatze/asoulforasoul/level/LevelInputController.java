package com.koenigkatze.asoulforasoul.level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.koenigkatze.asoulforasoul.media.music.MusicMessageData;
import com.koenigkatze.asoulforasoul.media.music.StandardMusicKeys;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.MusicMessageCodes;
import com.koenigkatze.asoulforasoul.messages.codes.UiMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;
import com.koenigkatze.asoulforasoul.ui.UiMessageData;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public final class LevelInputController implements InputProcessor
{
	private final Map<Integer, Boolean> keyStatusMap;

	public LevelInputController()
	{
		super();
		keyStatusMap = new ConcurrentHashMap<>();
	}

	public boolean isKeyDown(final int keycode)
	{
		final Boolean isKeyDown = keyStatusMap.get(keycode);
		return (isKeyDown != null) ? isKeyDown : false;
	}

	@Override
	public boolean keyDown(final int keycode)
	{
		boolean keyProcessed = false;
		switch (keycode)
		{
		case Keys.W:
			keyStatusMap.put(Keys.W, true);
			keyProcessed = true;
			break;
		case Keys.A:
			keyStatusMap.put(Keys.A, true);
			keyProcessed = true;
			break;
		case Keys.S:
			keyStatusMap.put(Keys.S, true);
			keyProcessed = true;
			break;
		case Keys.D:
			keyStatusMap.put(Keys.D, true);
			keyProcessed = true;
			break;
		case Keys.E:
			keyStatusMap.put(Keys.E, true);
			keyProcessed = true;
			break;
		case Keys.M:
			keyStatusMap.put(Keys.M, true);
			keyProcessed = true;
			break;
		case Keys.N:
			keyStatusMap.put(Keys.N, true);
			keyProcessed = true;
			break;
		case Keys.T:
			keyStatusMap.put(Keys.T, true);
			keyProcessed = true;
			break;
		}
		return keyProcessed;
	}

	@Override
	public boolean keyUp(final int keycode)
	{
		boolean keyProcessed = false;
		switch (keycode)
		{
		case Keys.W:
			keyStatusMap.put(Keys.W, false);
			keyProcessed = true;
			break;
		case Keys.A:
			keyStatusMap.put(Keys.A, false);
			keyProcessed = true;
			break;
		case Keys.S:
			keyStatusMap.put(Keys.S, false);
			keyProcessed = true;
			break;
		case Keys.D:
			keyStatusMap.put(Keys.D, false);
			keyProcessed = true;
			break;
		case Keys.E:
			keyStatusMap.put(Keys.D, false);
			keyProcessed = true;
			Messages.publish(EntityMessageCodes.PLAYER_INTERACT);
			break;	
		case Keys.M:
			keyStatusMap.put(Keys.M, false);
			keyProcessed = true;
			Messages.publish(MusicMessageCodes.TOGGLE_MUTE_UNMUTE);
			break;
		case Keys.N:
			keyStatusMap.put(Keys.N, false);
			keyProcessed = true;
			Messages.publish(MusicMessageCodes.PLAY, MusicMessageData.builder(StandardMusicKeys.GOODBYE_OLD_FRIEND.get()).withLooped(true).build());
			break;
		case Keys.T:
			keyStatusMap.put(Keys.T, false);
			keyProcessed = true;
			Messages.publish(UiMessageCodes.DISPLAY_MESSAGE_BOX, UiMessageData.builder().withText("Hello world!").build());
			break;
		}
		return keyProcessed;
	}

	@Override
	public boolean keyTyped(final char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button)
	{
		return false;
	}

	@Override
	public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button)
	{
		Messages.publish(EntityMessageCodes.PLAYER_INTERACT);
		return false;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY, final int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(final int screenX, final int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(final int amount)
	{
		return false;
	}
}
