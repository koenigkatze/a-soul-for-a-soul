package com.koenigkatze.asoulforasoul.maps.general;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum MapTypes implements MapProperty
{
	OUTSIDE("outside"), ;

	private final String tag;

	MapTypes(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}
	
	public static MapTypes map(final String value) throws UnknownMapPropertyValueException {
		MapTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value.toUpperCase());
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}

}
