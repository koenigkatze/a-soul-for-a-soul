package com.koenigkatze.asoulforasoul.level;

import java.util.ArrayList;
import java.util.List;

public class InteractionTarget implements SensorTarget
{
	private final Runnable action;
	private final InteractionBox interactionBox;
	private final List<InteractionStateListener> stateListeners;
	private boolean interactable;

	public InteractionTarget(Runnable action, InteractionBox interactionBox)
	{
		super();
		this.action = action;
		this.interactionBox = interactionBox;
		stateListeners = new ArrayList<>();
	}

	public boolean isInteractable()
	{
		return interactable;
	}

	public InteractionBox getInteractionBox()
	{
		return interactionBox;
	}

	public void interact()
	{
		action.run();
	}

	@Override
	public void beginContact()
	{
		if (!interactable)
		{
			interactable = true;
			notifyListeners();
		}
	}

	@Override
	public void endContact()
	{
		interactable = false;
		notifyListeners();
	}
	
	private void notifyListeners()
	{
		for (InteractionStateListener listener : stateListeners) {
			listener.stateChanged(interactable);
		}
	}

	public void addListener(InteractionStateListener listener)
	{
		stateListeners.add(listener);
	}

}
