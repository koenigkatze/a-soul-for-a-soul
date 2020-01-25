package com.koenigkatze.asoulforasoul.entities.sounds;

import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.entities.states.StateComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public final class SoundSystem extends IteratingSystem
{
	private final ComponentMapper<StateComponent> stateMapper;
	private final ComponentMapper<SoundComponent> soundMapper;

	public SoundSystem(final Family family)
	{
		super(family);
		stateMapper = ComponentMapper.getFor(StateComponent.class);
		soundMapper = ComponentMapper.getFor(SoundComponent.class);
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime)
	{
		final StateComponent stateComponent = stateMapper.get(entity);
		final EntityStates state = stateComponent.getState();
		
		final SoundComponent soundComponent = soundMapper.get(entity);
		if (soundComponent.hasStateBeenChanged(state)) {
			soundComponent.play(state);
		}
	}

}
