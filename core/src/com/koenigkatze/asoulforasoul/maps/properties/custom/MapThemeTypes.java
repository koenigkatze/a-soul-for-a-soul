package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum MapThemeTypes implements MapProperty {
	NONE("none");

	private final String tag;

	MapThemeTypes(final String tag) {
		this.tag = tag;
	}

	@Override
	public String getTag() {
		return tag;
	}

	public static MapThemeTypes map(String value) throws UnknownMapPropertyValueException {
		if ("".equals(value)) {
			throw new MissingMapPropertyException("Map property '"+CustomMapPropertyTypes.THEME.getTag()+"' must not be empty!");
		}
		MapThemeTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value.toUpperCase());
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
