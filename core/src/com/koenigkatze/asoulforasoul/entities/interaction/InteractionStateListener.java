package com.koenigkatze.asoulforasoul.entities.interaction;

@FunctionalInterface
public interface InteractionStateListener
{

	public void stateChanged(boolean interactable);

}
