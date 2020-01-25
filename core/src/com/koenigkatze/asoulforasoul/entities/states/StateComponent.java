package com.koenigkatze.asoulforasoul.entities.states;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class StateComponent implements Component, Poolable
{
	private EntityStates state;

	public StateComponent(final EntityStates state)
	{
		super();
		this.state = state;
	}

	public EntityStates getState()
	{
		return state;
	}

	public void setState(final EntityStates state)
	{
		this.state = state;
	}

	@Override
	public void reset()
	{
		state = EntityStates.STAND;
	}

}
