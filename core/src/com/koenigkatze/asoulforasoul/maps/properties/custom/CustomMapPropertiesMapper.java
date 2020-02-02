package com.koenigkatze.asoulforasoul.maps.properties.custom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.maps.MapProperties;
import com.koenigkatze.asoulforasoul.maps.MapEnvironmentTypes;
import com.koenigkatze.asoulforasoul.maps.MapTerrainTypes;
import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;

public final class CustomMapPropertiesMapper {
	private static final Set<CustomMapPropertyTypes> OUTSIDE_PROPERTIES = new HashSet<>(Arrays.asList(
			CustomMapPropertyTypes.ENVIRONMENT, CustomMapPropertyTypes.TERRAINTYPE, CustomMapPropertyTypes.THEME));

	public static CustomMapProperties loadOutsideProperties(String mapName, final MapProperties mapProperties) {
		testProperties(mapName, mapProperties);
		final CustomMapProperties customMapProperties = new CustomMapProperties();

		final String environmentValue = (String) mapProperties.get(CustomMapPropertyTypes.ENVIRONMENT.getTag());
		customMapProperties.setEnvironment(MapEnvironmentTypes.map(environmentValue));

		final String terrainTypeValue = (String) mapProperties.get(CustomMapPropertyTypes.TERRAINTYPE.getTag());
		customMapProperties.setTerrainType(MapTerrainTypes.map(terrainTypeValue));

		final String themeValue = (String) mapProperties.get(CustomMapPropertyTypes.THEME.getTag());
		customMapProperties.setTheme(themeValue);

		return customMapProperties;
	}

	private static void testProperties(final String mapName, final MapProperties mapProperties) {
		for (final CustomMapPropertyTypes singleProperty : OUTSIDE_PROPERTIES) {
			if (!mapProperties.containsKey(singleProperty.getTag()))
				throw new MissingMapPropertyException(
						"Map property missing: '" + singleProperty.getTag() + "' for map type 'outside' in map with name '" + mapName + "'. "
								+ "This property is mandatory.");
		}
	}

}
