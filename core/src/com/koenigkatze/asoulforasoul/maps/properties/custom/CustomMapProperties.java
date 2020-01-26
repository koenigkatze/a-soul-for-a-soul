package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.MapEnvironmentTypes;
import com.koenigkatze.asoulforasoul.maps.MapTerrainTypes;

public final class CustomMapProperties
{
	private MapEnvironmentTypes environment;
	private MapTerrainTypes terrainType;
	private MapThemeTypes theme;

	public MapEnvironmentTypes getEnvironment()
	{
		return environment;
	}

	public void setEnvironment(final MapEnvironmentTypes environment)
	{
		this.environment = environment;
	}

	public MapTerrainTypes getTerrainType()
	{
		return terrainType;
	}

	public void setTerrainType(final MapTerrainTypes terrainType)
	{
		this.terrainType = terrainType;
	}

	public MapThemeTypes getTheme()
	{
		return theme;
	}

	public void setTheme(final MapThemeTypes theme)
	{
		this.theme = theme;
	}

}
