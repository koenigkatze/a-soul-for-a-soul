package com.koenigkatze.asoulforasoul.entities.players;

import com.koenigkatze.asoulforasoul.entities.directions.DirectionalComponent;
import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.entities.states.StateComponent;
import com.koenigkatze.asoulforasoul.level.LevelInputController;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public final class PlayerControlSystem extends IteratingSystem
{
	private final ComponentMapper<BodyComponent> bodyMapper;
	private final ComponentMapper<StateComponent> stateMapper;
	private final ComponentMapper<DirectionalComponent> directionMapper;
	private final LevelInputController input;

	public PlayerControlSystem(final Family family, final LevelInputController input)
	{
		super(family);
		bodyMapper = ComponentMapper.getFor(BodyComponent.class);
		stateMapper = ComponentMapper.getFor(StateComponent.class);
		directionMapper = ComponentMapper.getFor(DirectionalComponent.class);
		this.input = input;
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime)
	{
		final StateComponent stateComponent = stateMapper.get(entity);
		final DirectionalComponent directionComponent = directionMapper.get(entity);

		final BodyComponent bodyComponent = bodyMapper.get(entity);
		final Body body = bodyComponent.getBody();
		if (input.isKeyDown(Keys.W) && input.isKeyDown(Keys.A))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.DIAGONAL_SPEED_NEGATIVE, PlayerConstants.DIAGONAL_SPEED));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.LEFT);
		}
		else if (input.isKeyDown(Keys.A) && input.isKeyDown(Keys.S))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.DIAGONAL_SPEED_NEGATIVE, PlayerConstants.DIAGONAL_SPEED_NEGATIVE));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.LEFT);
		}
		else if (input.isKeyDown(Keys.S) && input.isKeyDown(Keys.D))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.DIAGONAL_SPEED, PlayerConstants.DIAGONAL_SPEED_NEGATIVE));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.RIGHT);
		}
		else if (input.isKeyDown(Keys.D) && input.isKeyDown(Keys.W))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.DIAGONAL_SPEED, PlayerConstants.DIAGONAL_SPEED));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.RIGHT);
		}
		else if (input.isKeyDown(Keys.W))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.ZERO_SPEED, PlayerConstants.FULL_SPEED));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.UP);
		}
		else if (input.isKeyDown(Keys.A))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.FULL_SPEED_NEGATIVE, PlayerConstants.ZERO_SPEED));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.LEFT);
		}
		else if (input.isKeyDown(Keys.S))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.ZERO_SPEED, PlayerConstants.FULL_SPEED_NEGATIVE));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.DOWN);
		}
		else if (input.isKeyDown(Keys.D))
		{
			body.setType(BodyType.DynamicBody);
			body.setLinearVelocity(new Vector2(PlayerConstants.FULL_SPEED, PlayerConstants.ZERO_SPEED));
			stateComponent.setState(EntityStates.WALK);
			directionComponent.setDirection(EntityDirections.RIGHT);
		}
		else
		{
			body.setLinearVelocity(new Vector2(PlayerConstants.ZERO_SPEED, PlayerConstants.ZERO_SPEED));
			body.setType(BodyType.StaticBody);
			stateComponent.setState(EntityStates.STAND);
		}

	}

}
