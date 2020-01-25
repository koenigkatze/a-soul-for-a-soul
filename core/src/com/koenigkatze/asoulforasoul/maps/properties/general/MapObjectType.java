package com.koenigkatze.asoulforasoul.maps.properties.general;

public enum MapObjectType implements MapPropertyType
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
}
