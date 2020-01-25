package com.koenigkatze.asoulforasoul.media.music;

import com.badlogic.gdx.audio.Music;

public class MusicController
{
	private String currentName;
	private Music currentTrack;
	private float volume;
	private boolean muted;

	public MusicController()
	{
		super();
		volume = 0.5f;
	}

	public void toggleMute()
	{
		if (muted)
		{
			muted = false;
			currentTrack.setVolume(volume);
			return;
		}
		if (currentTrack != null && currentTrack.isPlaying())
		{
			volume = currentTrack.getVolume();
			currentTrack.setVolume(0.0f);
		}
		muted = true;
	}

	public void play(String name, boolean looped)
	{
		if (currentName != null && currentName.equals(name))
		{
			return;
		}
		final Music newTrack = MusicRepositories.getStandardRepository().get(StandardMusicKeys.valueOf(name.toUpperCase()));
		currentTrack = newTrack;
		if (muted)
		{
			currentTrack.setVolume(0.0f);
		}
		else
		{
			currentTrack.setVolume(volume);
		}
		if (looped)
		{
			currentTrack.setLooping(true);
		}
		currentTrack.play();
	}

}
