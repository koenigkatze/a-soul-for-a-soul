package com.koenigkatze.asoulforasoul.entities.interaction;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool.Poolable;

public class InteractionComponent implements Component, Poolable
{
	private final InteractionTarget target;
	private final Sprite icon;

	public InteractionComponent(InteractionTarget target, Sprite icon)
	{
		super();
		this.target = target;
		this.icon = icon;
	}

	public boolean isInteractable()
	{
		return target.isInteractable();
	}

	public InteractionTarget getTarget()
	{
		return target;
	}

	public Sprite getIcon()
	{
		return icon;
	}

	@Override
	public void reset()
	{
		// Do nothing.
	}

}
