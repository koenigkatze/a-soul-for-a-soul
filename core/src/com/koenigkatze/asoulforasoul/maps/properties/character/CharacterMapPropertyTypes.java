package com.koenigkatze.asoulforasoul.maps.properties.character;

import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum CharacterMapPropertyTypes implements MapProperty
{
	TEXTURE_ATLAS("texture_atlas"), X("x"), Y("y"), STATE("state"), DIRECTION("direction");

	private final String tag;

	CharacterMapPropertyTypes(final String tag)
	{
		this.tag = tag;
	}

	@Override
	public String getTag()
	{
		return tag;
	}
	
	public static CharacterMapPropertyTypes map(String value) throws UnknownMapPropertyValueException {
		CharacterMapPropertyTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}

}
