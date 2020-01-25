package com.koenigkatze.asoulforasoul.media.sound;

import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class SoundResourcesLoader
{
	private final AssetManager assetManager;

	public SoundResourcesLoader(final AssetManager assetManager)
	{
		super();
		this.assetManager = assetManager;
	}

	public void loadSounds()
	{
		assetManager.load("sounds/ambient/beach.mp3", Sound.class);
		
		assetManager.load("sounds/entities/footsteps-grass.mp3", Sound.class);
		assetManager.load("sounds/entities/footsteps-sand.mp3", Sound.class);
		assetManager.finishLoading();
		
		// Alter! Das geht ja mal gar nicht!
		Repository<Sound> ambienceRepository = new SoundRepository(SoundRepositories.AMBIENCE_REPOSITORY_NAME);
		ambienceRepository.put(EntitySoundKeys.FOOTSTEPS_GRASS, assetManager.get("sounds/ambient/beach.mp3"));
		SoundRepositories.put(ambienceRepository.getName(), ambienceRepository);
		
		Repository<Sound> standardRepository = new SoundRepository(SoundRepositories.ENTITIES_REPOSITORY_NAME);
		standardRepository.put(EntitySoundKeys.FOOTSTEPS_GRASS, assetManager.get("sounds/entities/footsteps-grass.mp3"));
		standardRepository.put(EntitySoundKeys.FOOTSTEPS_SAND, assetManager.get("sounds/entities/footsteps-grass.mp3"));
		SoundRepositories.put(standardRepository.getName(), standardRepository);
	}
}
