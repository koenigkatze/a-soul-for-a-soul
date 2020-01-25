package com.koenigkatze.asoulforasoul.entities.sprites;

import java.util.Comparator;

import com.koenigkatze.asoulforasoul.entities.directions.DirectionalComponent;
import com.koenigkatze.asoulforasoul.entities.interaction.InteractionComponent;
import com.koenigkatze.asoulforasoul.entities.positions.PositionComponent;
import com.koenigkatze.asoulforasoul.entities.states.StateComponent;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class RenderSystem extends SortedIteratingSystem
{
	private final ComponentMapper<SpriteProviderComponent> spriteMapper;
	private final ComponentMapper<PositionComponent> positionMapper;
	private final ComponentMapper<StateComponent> stateMapper;
	private final ComponentMapper<DirectionalComponent> directionMapper;
	private final ComponentMapper<InteractionComponent> interactionMapper;
	private final OrthographicCamera camera;
	private final SpriteBatch batch;

	public RenderSystem(final Family family, final OrthographicCamera camera, final SpriteBatch batch,
			final Comparator<Entity> comparator)
	{
		super(family, comparator);
		this.camera = camera;
		this.batch = batch;

		spriteMapper = ComponentMapper.getFor(SpriteProviderComponent.class);
		stateMapper = ComponentMapper.getFor(StateComponent.class);
		directionMapper = ComponentMapper.getFor(DirectionalComponent.class);
		positionMapper = ComponentMapper.getFor(PositionComponent.class);
		interactionMapper = ComponentMapper.getFor(InteractionComponent.class);
	}

	@Override
	protected void processEntity(final Entity entity, final float deltaTime)
	{
		forceSort();
		final PositionComponent positionComponent = positionMapper.get(entity);
		final StateComponent stateComponent = stateMapper.get(entity);
		final DirectionalComponent directionComponent = directionMapper.get(entity);

		final SpriteProviderComponent spriteProviderComponent = spriteMapper.get(entity);
		spriteProviderComponent.update(positionComponent.getPosition(), stateComponent.getState(),
				directionComponent.getDirection(), deltaTime);
		final Sprite sprite = spriteProviderComponent.getSprite();

		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();

		batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(), sprite.getWidth(),
				sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());

		final InteractionComponent interactionComponent = interactionMapper.get(entity);
		if (interactionComponent != null) {
			if (interactionComponent.isInteractable()) {
				final Sprite icon = interactionComponent.getIcon();
				batch.draw(icon, icon.getX(), icon.getY(), icon.getOriginX(), icon.getOriginY(), icon.getWidth(),
						icon.getHeight(), icon.getScaleX(), icon.getScaleY(), icon.getRotation());
			}
		}
		
		batch.end();
	}

}
