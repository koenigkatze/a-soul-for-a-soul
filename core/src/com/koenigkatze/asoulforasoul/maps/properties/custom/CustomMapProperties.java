package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.MapEnvironmentTypes;
import com.koenigkatze.asoulforasoul.maps.MapTerrainTypes;

import com.badlogic.gdx.audio.Music;

public final class CustomMapProperties
{
	private MapEnvironmentTypes environment;
	private MapTerrainTypes terrainType;
	private Music theme;

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

	public Music getTheme()
	{
		return theme;
	}

	public void setTheme(final Music theme)
	{
		this.theme = theme;
	}

}
