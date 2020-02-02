package com.koenigkatze.asoulforasoul.maps.properties.character;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.game.world.Coordinate2d;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public final class CharacterMapProperties
{
	private Coordinate2d position;
	private EntityStates state;
	private EntityDirections direction;
	private Repository<Animation<AtlasRegion>> repository;

	public Coordinate2d getPosition()
	{
		return position;
	}

	public void setPosition(final Coordinate2d position)
	{
		this.position = position;
	}

	public EntityStates getState()
	{
		return state;
	}

	public void setState(final EntityStates state)
	{
		this.state = state;
	}

	public EntityDirections getDirection()
	{
		return direction;
	}

	public void setDirection(final EntityDirections direction)
	{
		this.direction = direction;
	}

	public Repository<Animation<AtlasRegion>> getAnimationRepository()
	{
		return repository;
	}

	public void setAnimationRepository(final Repository<Animation<AtlasRegion>> repository)
	{
		this.repository = repository;
	}

	@Override
	public String toString()
	{
		return "CharacterMapProperties [position=" + position + ", state=" + state + ", direction=" + direction
				+ ", repository=" + repository + "]";
	}
	
	

}
