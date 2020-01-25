package com.koenigkatze.asoulforasoul.entities.positions;

import com.koenigkatze.asoulforasoul.constants.ConversionConstants;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public final class PositionSystem extends IteratingSystem
{
	private final ComponentMapper<PositionComponent> positionMapper;
	private final ComponentMapper<BodyComponent> bodyMapper;

	public PositionSystem(final Family family)
	{
		super(family);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		bodyMapper = ComponentMapper.getFor(BodyComponent.class);
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime)
	{
		final PositionComponent positionComponent = positionMapper.get(entity);

		final BodyComponent bodyComponent = bodyMapper.get(entity);
		final Body body = bodyComponent.getBody();
		final Vector2 bodyPosition = body.getPosition();

		positionComponent.setPositionIn2d(bodyPosition.x * ConversionConstants.PIXELS_TO_METERS,
				bodyPosition.y * ConversionConstants.PIXELS_TO_METERS);

	}
}
