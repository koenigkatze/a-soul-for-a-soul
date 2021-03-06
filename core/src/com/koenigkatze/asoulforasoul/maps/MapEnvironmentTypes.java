package com.koenigkatze.asoulforasoul.maps;

import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapPropertyTypes;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum MapEnvironmentTypes implements MapProperty
{
	BEACH("beach"), FOREST("forest");

	private final String tag;

	MapEnvironmentTypes(final String tag) {
		this.tag = tag;
	}

	@Override
	public String getTag() {
		return tag;
	}
	
	public static MapEnvironmentTypes map(final String value) throws UnknownMapPropertyValueException {
		if ("".equals(value)) {
			throw new MissingMapPropertyException("Map property '"+CustomMapPropertyTypes.ENVIRONMENT.getTag()+"' must not be empty!");
		}
		MapEnvironmentTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value.toUpperCase());
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
