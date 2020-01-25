package com.koenigkatze.asoulforasoul.maps.properties.info;

import com.koenigkatze.asoulforasoul.maps.properties.general.MapPropertyType;

public enum InfoObjectMapProperties implements MapPropertyType
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

}
