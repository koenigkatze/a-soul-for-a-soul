package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.properties.general.MapPropertyType;

public enum CustomMapPropertyTypes implements MapPropertyType
{
	ENVIRONMENT("environment"), TERRAINTYPE("terraintype"), THEME("theme");
	
	private final String tag;

	CustomMapPropertyTypes(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}
}
