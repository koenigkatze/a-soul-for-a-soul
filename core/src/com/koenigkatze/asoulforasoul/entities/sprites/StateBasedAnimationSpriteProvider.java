package com.koenigkatze.asoulforasoul.entities.sprites;

import java.util.NoSuchElementException;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.media.animations.AnimationKeys;
import com.koenigkatze.asoulforasoul.media.repositories.Repository;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;

public final class StateBasedAnimationSpriteProvider implements SpriteProvider
{
	private final Repository<Animation<AtlasRegion>> repository;
	private AnimationKeys currentKey;
	private Animation<AtlasRegion> currentAnimation;
	private float elapsedTime;
	private Sprite sprite;

	public StateBasedAnimationSpriteProvider(final Repository<Animation<AtlasRegion>> repository)
	{
		this.repository = repository;
		sprite = new Sprite(repository.get(AnimationKeys.DOWN_STAND).getKeyFrame(0.0f));
	}

	@Override
	public Sprite getSprite()
	{
		return sprite;
	}

	@Override
	public void update(final Vector3 position, final EntityStates state, final EntityDirections direction,
			final float deltaTime)
	{
		final AnimationKeys animationKey = AnimationKeys.get(state, direction);
		final AtlasRegion animation = getCurrentAnimation(animationKey, deltaTime);
		sprite = new Sprite(animation);
		sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 8);
	}

	public AtlasRegion getCurrentAnimation(final AnimationKeys key, final float deltaTime)
	{
		if (key.equals(currentKey))
		{
			elapsedTime += deltaTime;
			return currentAnimation.getKeyFrame(elapsedTime, true);
		}

		elapsedTime = 0.0f;

		final Animation<AtlasRegion> animation = repository.get(key);
		if (animation == null)
		{
			throw new NoSuchElementException("No animation in repository for key: " + key);
		}

		currentKey = key;
		currentAnimation = animation;
		return animation.getKeyFrame(elapsedTime, true);
	}

}
