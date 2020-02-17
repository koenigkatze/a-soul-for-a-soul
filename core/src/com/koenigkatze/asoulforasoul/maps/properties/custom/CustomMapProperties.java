package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.MapEnvironmentTypes;
import com.koenigkatze.asoulforasoul.maps.MapTerrainTypes;

public final class CustomMapProperties
{
	private MapEnvironmentTypes environmentType;
	private MapTerrainTypes terrainType;
	private String theme;
	private CameraTypes cameraType;

	public MapEnvironmentTypes getEnvironmentType()
	{
		return environmentType;
	}

	public void setEnvironmentType(final MapEnvironmentTypes environment)
	{
		this.environmentType = environment;
	}

	public MapTerrainTypes getTerrainType()
	{
		return terrainType;
	}

	public void setTerrainType(final MapTerrainTypes terrainType)
	{
		this.terrainType = terrainType;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(final String theme)
	{
		this.theme = theme;
	}

	public CameraTypes getCameraType() {
		return cameraType;
	}

	public void setCameraType(final CameraTypes cameraType) {
		this.cameraType = cameraType;
	}

}
