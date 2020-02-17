package com.koenigkatze.asoulforasoul.entities;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.koenigkatze.asoulforasoul.entities.interaction.SensorTarget;

public class EntityContactListener implements ContactListener
{

	@Override
	public void beginContact(Contact contact)
	{
		final Fixture fixtureA = contact.getFixtureA();
		final Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.isSensor() && !(fixtureB.isSensor()))
		{
			final Object userData = fixtureA.getBody().getUserData();
			if (userData instanceof SensorTarget)
			{
				final SensorTarget target = (SensorTarget) userData;
				target.beginContact();
			}
		}
		else if (!(fixtureA.isSensor()) && fixtureB.isSensor())
		{
			final Object userData = fixtureB.getBody().getUserData();
			if (userData instanceof SensorTarget)
			{
				final SensorTarget target = (SensorTarget) userData;
				target.beginContact();
			}
		}
	}

	@Override
	public void endContact(Contact contact)
	{
		final Fixture fixtureA = contact.getFixtureA();
		final Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.isSensor() && !(fixtureB.isSensor()))
		{
			final Object userData = fixtureA.getBody().getUserData();
			if (userData instanceof SensorTarget)
			{
				final SensorTarget target = (SensorTarget) userData;
				target.endContact();
			}
		}
		else if (!(fixtureA.isSensor()) && fixtureB.isSensor())
		{
			final Object userData = fixtureB.getBody().getUserData();
			if (userData instanceof SensorTarget)
			{
				final SensorTarget target = (SensorTarget) userData;
				target.endContact();
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		// Do nothing.
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		// Do nothing.
	}

}
