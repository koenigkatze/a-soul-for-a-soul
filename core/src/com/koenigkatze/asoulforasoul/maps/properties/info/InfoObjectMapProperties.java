package com.koenigkatze.asoulforasoul.maps.properties.info;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum InfoObjectMapProperties implements MapProperty
{
	INFOTEXT("infotext");

	private final String tag;

	InfoObjectMapProperties(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}
	
	public static InfoObjectMapProperties map(String value) throws UnknownMapPropertyValueException{
		InfoObjectMapProperties possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}

}
