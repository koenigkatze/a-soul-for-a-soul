package com.koenigkatze.asoulforasoul.media.music;

import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

public enum StandardMusicKeys implements RepositoryKey
{
	GOODBYE_OLD_FRIEND("GOODBYE_OLD_FRIEND");
	
	private final String key;

	StandardMusicKeys(String key)
	{
		this.key = key;
	}

	@Override
	public String get()
	{
		return key;
	}
}
