package com.koenigkatze.asoulforasoul.maps.properties.general;

public enum GeneralMapObjectProperties implements MapPropertyType
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

}
