package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum CustomMapPropertyTypes implements MapProperty
{
	ENVIRONMENT("environment"), TERRAINTYPE("terraintype"), THEME("theme"), CAMERA("camera");
	
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
	
	public static CustomMapPropertyTypes map(String value) throws UnknownMapPropertyValueException {
		CustomMapPropertyTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}

}
