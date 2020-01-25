package com.koenigkatze.asoulforasoul.maps.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

public final class MapAccess
{
	private final TiledMap map;

	public MapAccess(final TiledMap map)
	{
		super();
		this.map = map;
	}

	public Optional<Coordinate2d> findObjectInLayer(final String objectsName, final String layerName)
	{
		Objects.requireNonNull(objectsName);
		Objects.requireNonNull(layerName);

		final MapLayer positionObjectMapLayer = map.getLayers().get(layerName);
		if (positionObjectMapLayer == null)
		{
			return Optional.empty();
		}

		for (final MapObject mapObject : positionObjectMapLayer.getObjects())
		{
			if (objectsName.equals(mapObject.getName()))
			{
				if (mapObject instanceof RectangleMapObject)
				{
					final RectangleMapObject tempObject = (RectangleMapObject) mapObject;
					return Optional
							.of(Coordinate2d.of(tempObject.getRectangle().getX(), tempObject.getRectangle().getY()));
				}

			}
		}

		return Optional.empty();
	}

	public Collection<RectangleMapObject> getRectangleMapObjectsForLayer(final String layerName)
	{
		final MapLayer mapObjectsLayer = map.getLayers().get(layerName);
		if (mapObjectsLayer == null)
		{
			return Collections.emptyList();
		}

		final List<RectangleMapObject> listOfObjects = new ArrayList<>();
		for (final MapObject mapObject : mapObjectsLayer.getObjects())
		{
			if (mapObject instanceof RectangleMapObject)
			{
				listOfObjects.add((RectangleMapObject) mapObject);
			}

		}
		return listOfObjects;
	}

	public Collection<Coordinate2d> getAllObjectCoordinatesFromLayer(final String layerName)
	{
		Objects.requireNonNull(layerName);

		final MapLayer desiredObjectLayer = map.getLayers().get(layerName);
		if (desiredObjectLayer == null)
		{
			return Collections.emptyList();
		}

		final List<Coordinate2d> collectedCoordinates = new ArrayList<>();
		for (final MapObject mapObject : desiredObjectLayer.getObjects())
		{
			if (mapObject instanceof RectangleMapObject)
			{
				final RectangleMapObject tempObject = (RectangleMapObject) mapObject;
				collectedCoordinates
						.add(Coordinate2d.of(tempObject.getRectangle().getX(), tempObject.getRectangle().getY()));
			}
		}
		return collectedCoordinates;
	}

	public Collection<MapObject> getAllObjectsForLayer(final String layerName)
	{
		Objects.requireNonNull(layerName);

		final MapLayer desiredObjectLayer = map.getLayers().get(layerName);
		if (desiredObjectLayer == null)
		{
			return Collections.emptyList();
		}

		final List<MapObject> collectedObjects = new ArrayList<>();
		for (final MapObject mapObject : desiredObjectLayer.getObjects())
		{
			collectedObjects.add(mapObject);
		}
		return collectedObjects;
	}
}
