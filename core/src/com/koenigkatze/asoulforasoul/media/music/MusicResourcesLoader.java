package com.koenigkatze.asoulforasoul.media.music;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class MusicResourcesLoader
{
	private final AssetManager assetManager;

	public MusicResourcesLoader(final AssetManager assetManager)
	{
		super();
		this.assetManager = assetManager;
	}

	public void loadMusic()
	{
		assetManager.load("music/Dark Fantasy Studio - Goodbye my friend.mp3", Music.class);
		assetManager.finishLoading();
		
		MusicRepository standardRepository = new MusicRepository("standard");
		standardRepository.put(StandardMusicKeys.GOODBYE_OLD_FRIEND, assetManager.get("music/Dark Fantasy Studio - Goodbye my friend.mp3"));
		
		MusicRepositories.put(standardRepository.getName(), standardRepository);
	}
	
	
}
