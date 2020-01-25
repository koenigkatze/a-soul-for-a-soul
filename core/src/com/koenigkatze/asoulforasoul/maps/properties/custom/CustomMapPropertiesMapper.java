package com.koenigkatze.asoulforasoul.maps.properties.custom;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.koenigkatze.asoulforasoul.maps.MapEnvironmentTypes;
import com.koenigkatze.asoulforasoul.maps.MapTerrainTypes;
import com.koenigkatze.asoulforasoul.maps.exceptions.MapObjectMissingAttributeException;
import com.koenigkatze.asoulforasoul.media.music.MusicRepositories;
import com.koenigkatze.asoulforasoul.media.music.StandardMusicKeys;

import com.badlogic.gdx.maps.MapProperties;

public final class CustomMapPropertiesMapper
{
	private final Set<CustomMapPropertyTypes> mandatoryProperties;

	public CustomMapPropertiesMapper()
	{
		super();
		mandatoryProperties = Stream
				.of(CustomMapPropertyTypes.ENVIRONMENT, CustomMapPropertyTypes.TERRAINTYPE, CustomMapPropertyTypes.THEME)
				.collect(Collectors.toSet());
	}
	
	public CustomMapProperties loadProperties(final MapProperties mapProperties)
	{
		testProperties(mapProperties);
		final CustomMapProperties customMapProperties = new CustomMapProperties();
		
		final String environmentValue = (String) mapProperties.get(CustomMapPropertyTypes.ENVIRONMENT.getTag());
		customMapProperties.setEnvironment(MapEnvironmentTypes.valueOf(environmentValue.toUpperCase()));

		final String terrainTypeValue = (String) mapProperties.get(CustomMapPropertyTypes.TERRAINTYPE.getTag());
		customMapProperties.setTerrainType(MapTerrainTypes.valueOf(terrainTypeValue.toUpperCase()));

		final String themeValue = (String) mapProperties.get(CustomMapPropertyTypes.THEME.getTag());
		if (!themeValue.equalsIgnoreCase("NONE")) {
			customMapProperties.setTheme(MusicRepositories.getStandardRepository().get(StandardMusicKeys.valueOf(themeValue)));
		}
		
		return customMapProperties;
	}
	
	private void testProperties(final MapProperties mapProperties)
	{
		for (final CustomMapPropertyTypes singleProperty : mandatoryProperties) {
			if (!mapProperties.containsKey(singleProperty.getTag()))
				throw new MapObjectMissingAttributeException();
		}
	}
	
	
}
