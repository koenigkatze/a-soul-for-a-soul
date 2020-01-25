package com.koenigkatze.asoulforasoul.entities.sprites;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public interface SpriteProvider
{

	Sprite getSprite();

	void update(Vector3 position, EntityStates state, EntityDirections direction, float deltaTime);

}
