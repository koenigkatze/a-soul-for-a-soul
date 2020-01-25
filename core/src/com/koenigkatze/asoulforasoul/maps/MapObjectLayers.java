package com.koenigkatze.asoulforasoul.maps;

public enum MapObjectLayers
{
	BLOCKED("blocked"), POSITIONS("positions"), PASSIVE_MAP_OBJECTS("passive_map_objects"), ACTIVE_MAP_OBJECTS("active_map_objects");

	private String layerName;

	MapObjectLayers(final String layerName)
	{
		this.layerName = layerName;
	}

	public String getName()
	{
		return layerName;
	}
}
