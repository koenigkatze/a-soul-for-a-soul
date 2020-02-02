package com.koenigkatze.asoulforasoul.maps;

public enum MapObjectLayers
{
	BLOCKED("blocked"), ENTITY_STARTING_POSITIONS("entity_starting_positions"), PASSIVE_MAP_OBJECTS("passive_map_objects"), ACTIVE_MAP_OBJECTS("active_map_objects");

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
