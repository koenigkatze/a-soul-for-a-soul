package com.koenigkatze.asoulforasoul.entities.utils;

import java.util.ArrayList;
import java.util.List;

import com.koenigkatze.asoulforasoul.entities.directions.DirectionalComponent;
import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.interaction.InteractionComponent;
import com.koenigkatze.asoulforasoul.entities.physics.BodyComponent;
import com.koenigkatze.asoulforasoul.entities.players.PlayerComponent;
import com.koenigkatze.asoulforasoul.entities.positions.PositionComponent;
import com.koenigkatze.asoulforasoul.entities.sounds.SoundComponent;
import com.koenigkatze.asoulforasoul.entities.sprites.SimpleSpriteProvider;
import com.koenigkatze.asoulforasoul.entities.sprites.SpriteProviderComponent;
import com.koenigkatze.asoulforasoul.entities.sprites.StateBasedAnimationSpriteProvider;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.entities.states.StateComponent;
import com.koenigkatze.asoulforasoul.level.InteractionTarget;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public final class EntityBuilder
{
	private final List<Component> componentsToBuild;

	private EntityBuilder()
	{
		componentsToBuild = new ArrayList<>();
	}

	public static EntityBuilder get()
	{
		return new EntityBuilder();
	}

	public EntityBuilder withBodyComponent(final Body body)
	{
		componentsToBuild.add(new BodyComponent(body));
		return this;
	}

	public EntityBuilder withPositionComponent(final Vector3 position)
	{
		componentsToBuild.add(new PositionComponent(position));
		return this;
	}

	public EntityBuilder withPlayerComponent()
	{
		componentsToBuild.add(new PlayerComponent());
		return this;
	}

	public EntityBuilder withStateComponent(final EntityStates state)
	{
		componentsToBuild.add(new StateComponent(state));
		return this;
	}

	public EntityBuilder withDirectionalComponent(final EntityDirections direction)
	{
		componentsToBuild.add(new DirectionalComponent(direction));
		return this;
	}
	
	public EntityBuilder withSimpleSpriteProviderComponent(final Sprite sprite)
	{
		componentsToBuild.add(new SpriteProviderComponent(new SimpleSpriteProvider(sprite)));
		return this;
	}
	
	public EntityBuilder withAnimationBasedSpriteProviderComponent(final Repository<Animation<AtlasRegion>> repository)
	{
		componentsToBuild.add(new SpriteProviderComponent(new StateBasedAnimationSpriteProvider(repository)));
		return this;
	}
	
	public EntityBuilder withSoundComponent(final Repository<Sound> repository)
	{
		componentsToBuild.add(new SoundComponent(repository));
		return this;
	}

	public EntityBuilder withInteractionComponent(final InteractionTarget interactionTarget, final Sprite icon)
	{
		componentsToBuild.add(new InteractionComponent(interactionTarget, icon));
		return this;
	}

	public Entity buildWithEngine(final PooledEngine engine)
	{
		final Entity entity = engine.createEntity();
		for (final Component buildPart : componentsToBuild)
		{
			entity.add(buildPart);
		}
		return entity;
	}


}
