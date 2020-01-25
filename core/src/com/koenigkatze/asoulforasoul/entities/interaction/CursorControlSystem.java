package com.koenigkatze.asoulforasoul.entities.interaction;

import com.koenigkatze.asoulforasoul.level.InteractionBox;
import com.koenigkatze.asoulforasoul.logging.Logging;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CursorControlSystem extends IteratingSystem
{
	private final OrthographicCamera camera;
	private final ComponentMapper<InteractionComponent> interactionMapper;

	public CursorControlSystem(final Family family, final OrthographicCamera camera)
	{
		super(family);
		this.camera = camera;
		interactionMapper = ComponentMapper.getFor(InteractionComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime)
	{
		final InteractionComponent interactionComponent = interactionMapper.get(entity);
		final InteractionBox interactionBox = interactionComponent.getTarget().getInteractionBox();
		
		final Vector3 project = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		final int mouseX = (int) project.x;
		final int mouseY = (int) project.y;

		if (interactionBox.isInBounds(mouseX, mouseY)) {
			if (interactionBox.isAlreadyInBounds()) {
				return;
			}
			interactionBox.setAlreadyInBounds(true);
			Messages.publish(EntityMessageCodes.MOUSE_OVER_INFO_OBJECT, interactionComponent.getTarget());
			Logging.logDebug(CursorControlSystem.class, "Mouse over for info object. Message sent.");
		} else if (interactionBox.isAlreadyInBounds()){
			interactionBox.setAlreadyInBounds(false);
			Messages.publish(EntityMessageCodes.MOUSE_OVER_RELEASED);
			Logging.logDebug(CursorControlSystem.class, "Mouse over for info object released.");
		}
		
	}
}
