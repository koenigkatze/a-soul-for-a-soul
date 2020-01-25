package com.koenigkatze.asoulforasoul.maps.properties.character;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.koenigkatze.asoulforasoul.entities.directions.EntityDirections;
import com.koenigkatze.asoulforasoul.entities.states.EntityStates;
import com.koenigkatze.asoulforasoul.maps.exceptions.MapObjectMissingAttributeException;
import com.koenigkatze.asoulforasoul.maps.general.Coordinate2d;
import com.koenigkatze.asoulforasoul.media.animations.AnimationRepositories;

import com.badlogic.gdx.maps.MapProperties;

public final class CharacterMapPropertiesMapper
{
	private final Set<CharacterMapPropertyTypes> mandatoryProperties;

	public CharacterMapPropertiesMapper()
	{
		super();
		mandatoryProperties = Stream.of(CharacterMapPropertyTypes.TEXTURE_ATLAS, CharacterMapPropertyTypes.X, CharacterMapPropertyTypes.Y,
				CharacterMapPropertyTypes.STATE, CharacterMapPropertyTypes.DIRECTION).collect(Collectors.toSet());
	}

	public CharacterMapProperties loadProperties(final MapProperties mapProperties)
	{
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

	private void testProperties(final MapProperties mapProperties)
	{
		for (final CharacterMapPropertyTypes singleProperty : mandatoryProperties) {
			if (!mapProperties.containsKey(singleProperty.getTag()))
				throw new MapObjectMissingAttributeException();
		}
		
	}

}
