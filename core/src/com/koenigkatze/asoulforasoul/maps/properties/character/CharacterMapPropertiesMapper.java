package com.koenigkatze.asoulforasoul.maps.properties.character;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.maps.MapProperties;
import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.game.world.Coordinate2d;
import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.media.animations.AnimationRepositories;

public final class CharacterMapPropertiesMapper {
	private static final Set<CharacterMapPropertyTypes> MANDATORY_PROPERTIES = new HashSet<>(Arrays.asList(
					CharacterMapPropertyTypes.TEXTURE_ATLAS, 
					CharacterMapPropertyTypes.X,
					CharacterMapPropertyTypes.Y,
					CharacterMapPropertyTypes.STATE, 
					CharacterMapPropertyTypes.DIRECTION));

	public static CharacterMapProperties loadProperties(final MapProperties mapProperties) {
		testProperties(mapProperties);
		final CharacterMapProperties characterProperties = new CharacterMapProperties();

		final float positionXValue = (float) mapProperties.get(CharacterMapPropertyTypes.X.getTag());
		final float positionYValue = (float) mapProperties.get(CharacterMapPropertyTypes.Y.getTag());
		characterProperties.setPosition(Coordinate2d.of(positionXValue, positionYValue));

		final String stateValue = (String) mapProperties.get(CharacterMapPropertyTypes.STATE.getTag());
		characterProperties.setState(EntityStates.valueOf(stateValue.toUpperCase()));

		final String directionValue = (String) mapProperties.get(CharacterMapPropertyTypes.DIRECTION.getTag());
		characterProperties.setDirection(EntityDirections.valueOf(directionValue.toUpperCase()));

		final String textureAtlasValue = (String) mapProperties.get(CharacterMapPropertyTypes.TEXTURE_ATLAS.getTag());
		characterProperties.setAnimationRepository(AnimationRepositories.get(textureAtlasValue));

		return characterProperties;
	}

	private static void testProperties(final MapProperties mapProperties) {
		for (final CharacterMapPropertyTypes singleProperty : MANDATORY_PROPERTIES) {
			if (!mapProperties.containsKey(singleProperty.getTag()))
				throw new MissingMapPropertyException();
		}

	}

}
