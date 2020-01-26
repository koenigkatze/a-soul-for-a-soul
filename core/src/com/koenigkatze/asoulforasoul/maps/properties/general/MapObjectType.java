package com.koenigkatze.asoulforasoul.maps.properties.general;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;

public enum MapObjectType implements MapProperty
{
	INFO_OBJECT("info_object");

	private final String tag;

	MapObjectType(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}
	
	public static MapObjectType map(String value) throws UnknownMapPropertyValueException{
		MapObjectType possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
