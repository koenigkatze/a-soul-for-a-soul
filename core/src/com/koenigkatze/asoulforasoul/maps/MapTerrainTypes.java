package com.koenigkatze.asoulforasoul.maps;

import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapPropertyTypes;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum MapTerrainTypes implements MapProperty {
	SAND("sand"), GRASS("grass");

	private final String tag;

	MapTerrainTypes(String tag) {
		this.tag = tag;
	}

	@Override
	public String getTag() {
		return tag;
	}
	
	public static MapTerrainTypes map(String value) throws UnknownMapPropertyValueException {
		if ("".equals(value)) {
			throw new MissingMapPropertyException("Map property '"+CustomMapPropertyTypes.TERRAINTYPE.getTag()+"' must not be empty!");
		}
		MapTerrainTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value.toUpperCase());
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
