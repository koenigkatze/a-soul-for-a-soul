package com.koenigkatze.asoulforasoul.entities.camera;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;
import com.koenigkatze.asoulforasoul.game.world.CoordinateConverter;

public final class CameraControlSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	private final ComponentMapper<BodyComponent> bodyMapper;
//	private final ComponentMapper<CameraComponent> cameraComponent;

	public CameraControlSystem(final Family family, final OrthographicCamera camera) {
		super(family);
		this.camera = camera;
		bodyMapper = ComponentMapper.getFor(BodyComponent.class);
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime) {
		final BodyComponent bodyComponent = bodyMapper.get(entity);
		final Body targetBody = bodyComponent.getBody();
		camera.position.set(
				CoordinateConverter.convertFromPhysicsScale(targetBody.getPosition().x),
				CoordinateConverter.convertFromPhysicsScale(targetBody.getPosition().y), 
				0);
		camera.update();
	}

}
