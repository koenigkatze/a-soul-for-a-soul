package com.koenigkatze.asoulforasoul.entities.debug;

import com.koenigkatze.asoulforasoul.constants.ConversionConstants;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Matrix4;

public final class DebugSystem extends IteratingSystem
{
	private final ComponentMapper<DebugComponent> debugMapper;

	public DebugSystem(final Family family)
	{
		super(family);
		debugMapper = ComponentMapper.getFor(DebugComponent.class);
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime)
	{
		final DebugComponent debugComponent = debugMapper.get(entity);
		final Matrix4 debugMatrix = debugComponent.getCamera().combined.scale(ConversionConstants.PIXELS_TO_METERS,
				ConversionConstants.PIXELS_TO_METERS, 0);
		debugComponent.getDebugRenderer().render(debugComponent.getWorld(), debugMatrix);
	}

}
