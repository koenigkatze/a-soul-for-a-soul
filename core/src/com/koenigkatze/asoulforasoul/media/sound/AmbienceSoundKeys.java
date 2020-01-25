package com.koenigkatze.asoulforasoul.media.sound;

import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

public enum AmbienceSoundKeys implements RepositoryKey
{
	BEACH("BEACH");
	
	private final String key;

	AmbienceSoundKeys(String key)
	{
		this.key = key;
	}

	@Override
	public String get()
	{
		return key;
	}
}
