package com.koenigkatze.asoulforasoul.maps.properties.general;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;

public enum GeneralMapObjectProperties implements MapProperty
{
	NAME("name"), TYPE("type");

	private final String tag;

	GeneralMapObjectProperties(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}

	public static GeneralMapObjectProperties map(String value) throws UnknownMapPropertyValueException{
		GeneralMapObjectProperties possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
