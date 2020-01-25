package com.koenigkatze.asoulforasoul.maps.properties.character;

import com.koenigkatze.asoulforasoul.maps.properties.general.MapPropertyType;

public enum CharacterMapPropertyTypes implements MapPropertyType
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

}
