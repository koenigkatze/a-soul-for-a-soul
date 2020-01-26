package com.koenigkatze.asoulforasoul.maps.general;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapProperties;

public final class MapData {

	private final TiledMap tiledMap;
	private final CustomMapProperties mapProperties;

	public MapData(final TiledMap tiledMap, final CustomMapProperties mapProperties) {
		this.tiledMap = tiledMap;
		this.mapProperties = mapProperties;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public CustomMapProperties getMapProperties() {
		return mapProperties;
	}

	@Override
	public String toString() {
		return "MapData [tiledMap=" + tiledMap + ", mapProperties=" + mapProperties + "]";
	}

}
