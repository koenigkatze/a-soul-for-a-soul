package com.koenigkatze.asoulforasoul.entities.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public final class BodyFactory
{
	private World world;

	public BodyFactory(final World world)
	{
		this.world = world;
	}

	public Body createRectangleBody(final float x, final float y, final float width, final float height,
			final BodyType type)
	{
		final BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		final Body body = world.createBody(bodyDef);

		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);

		final FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 10f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0f;

		body.createFixture(fixtureDef);
		shape.dispose();

		return body;
	}

	public Body createCharacterBody(final float x, final float y, final float width, final float height)
	{
		final BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		final Body body = world.createBody(bodyDef);
		
		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 3.5f, height / 4.5f);

		final FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 10f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0f;

		body.createFixture(fixtureDef);
		shape.dispose();

		return body;
	}

	public Body createInteractionSensor(final float x, final float y, final float width, final float height)
	{
		final BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		final Body body = world.createBody(bodyDef);

		final PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2.0f, height / 2.0f);

		final FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 10f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0f;
		fixtureDef.isSensor = true;

		body.createFixture(fixtureDef);
		shape.dispose();
		
		return body;
	}

	public void dispose()
	{
		world = null;
	}


}
