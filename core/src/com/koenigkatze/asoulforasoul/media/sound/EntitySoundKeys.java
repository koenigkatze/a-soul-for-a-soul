package com.koenigkatze.asoulforasoul.media.sound;

import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

public enum EntitySoundKeys implements RepositoryKey
{
	FOOTSTEPS_GRASS("FOOTSTEPS_GRASS"), FOOTSTEPS_SAND("FOOTSTEPS_SAND");

	private final String key;

	EntitySoundKeys(String key)
	{
		this.key = key;
	}

	@Override
	public String get()
	{
		return key;
	}

}
