package com.koenigkatze.asoulforasoul.entities.positions;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public final class VerticalAxisComparator implements Comparator<Entity>
{
	private final ComponentMapper<PositionComponent> componentMapper;

	public VerticalAxisComparator()
	{
		componentMapper = ComponentMapper.getFor(PositionComponent.class);
	}

	@Override
	public int compare(final Entity firstEntity, final Entity secondEntity)
	{
		final float firstEntityY = componentMapper.get(firstEntity).getPosition().y;
		final float secondEntityY = componentMapper.get(secondEntity).getPosition().y;

		int result = 0;
		if (firstEntityY < secondEntityY)
		{
			result = 1;
		}
		else if (firstEntityY> secondEntityY)
		{
			result = -1;
		}
		return result;
	}

}
