package com.koenigkatze.asoulforasoul.maps.general;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.koenigkatze.asoulforasoul.constants.FileConstants;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapTypeException;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapProperties;
import com.koenigkatze.asoulforasoul.maps.properties.custom.CustomMapPropertiesMapper;
import com.koenigkatze.asoulforasoul.maps.properties.general.GeneralMapObjectProperties;

public class MapLoader {
	public static MapData loadFromStandardPath(final String name) {
		final TiledMap tiledMap = new TmxMapLoader()
				.load(FileConstants.MAPS_STANDARD_PATH + name + FileConstants.TILD_MAP_FILE_ENDING);

		MapProperties properties = tiledMap.getProperties();
		final Object mapType = properties.get(GeneralMapObjectProperties.TYPE.getTag());
		if (!(mapType instanceof String)) {
			throw new IllegalStateException("Map could not be loaded! Malformed map type: " + mapType);
		}
		final MapTypes mappedMapType = MapTypes.map((String) mapType);
		CustomMapProperties mapProperties = null;
		
		switch (mappedMapType) {
		case OUTSIDE:
			mapProperties = CustomMapPropertiesMapper.loadOutsideProperties(name, properties);
			break;
		default:
			throw new UnknownMapTypeException("Map could not be loaded! Unknown map type: " + mappedMapType);
		}

		return new MapData(tiledMap, mapProperties);
	}
}
