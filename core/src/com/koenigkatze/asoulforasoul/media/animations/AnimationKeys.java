package com.koenigkatze.asoulforasoul.media.animations;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.media.repositories.RepositoryKey;

public enum AnimationKeys implements RepositoryKey
{
	UP_STAND("UP_STAND"), 
	LEFT_STAND("LEFT_STAND"), 
	DOWN_STAND("DOWN_STAND"), 
	RIGHT_STAND("RIGHT_STAND"), 
	UP_WALK("UP_WALK"), 
	LEFT_WALK("LEFT_WALK"), 
	DOWN_WALK("DOWN_WALK"), 
	RIGHT_WALK("RIGHT_WALK");

	private final String key;

	AnimationKeys(String key)
	{
		this.key = key;
	}

	@Override
	public String get()
	{
		return key;
	}
	
	public static AnimationKeys get(final EntityStates state, final EntityDirections direction)
	{
		switch (direction)
		{
		case DOWN:
			switch (state)
			{
			case STAND:
				return DOWN_STAND;
			case WALK:
				return DOWN_WALK;
			default:
				throw new IllegalArgumentException("Unknown state: " + state);
			}
		case LEFT:
			switch (state)
			{
			case STAND:
				return LEFT_STAND;
			case WALK:
				return LEFT_WALK;
			default:
				throw new IllegalArgumentException("Unknown state: " + state);
			}
		case RIGHT:
			switch (state)
			{
			case STAND:
				return RIGHT_STAND;
			case WALK:
				return RIGHT_WALK;
			default:
				throw new IllegalArgumentException("Unknown state: " + state);
			}
		case UP:
			switch (state)
			{
			case STAND:
				return UP_STAND;
			case WALK:
				return UP_WALK;
			default:
				throw new IllegalArgumentException("Unknown state: " + state);
			}
		default:
			throw new IllegalArgumentException("Unknown direction: " + direction);

		}
	}

}
