package com.koenigkatze.asoulforasoul.entities.sounds;

import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;
import com.koenigkatze.asoulforasoul.media.sound.EntitySoundKeys;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class SoundComponent implements Component, Poolable
{
	private final Repository<Sound> repository;
	private EntityStates currentState;
	private Sound currentSound;

	public SoundComponent(final Repository<Sound> repository)
	{
		super();
		this.repository = repository;
		currentState = EntityStates.STAND;
	}

	public boolean hasStateBeenChanged(final EntityStates state)
	{
		return state != currentState;
	}

	public void play(final EntityStates state)
	{
		currentState = state;
		if (currentSound != null)
		{
			currentSound.stop();
		}
		if (currentState == EntityStates.NONE || currentState == EntityStates.STAND)
		{
			return;
		}
		currentSound = repository.get(EntitySoundKeys.FOOTSTEPS_SAND);
		currentSound.loop();
	}

	@Override
	public void reset()
	{
		// Do nothing.
	}
}
