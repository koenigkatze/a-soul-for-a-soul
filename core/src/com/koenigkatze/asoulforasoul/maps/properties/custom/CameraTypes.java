package com.koenigkatze.asoulforasoul.maps.properties.custom;

import com.koenigkatze.asoulforasoul.maps.exceptions.MissingMapPropertyException;
import com.koenigkatze.asoulforasoul.maps.exceptions.UnknownMapPropertyValueException;
import com.koenigkatze.asoulforasoul.maps.properties.general.MapProperty;

public enum CameraTypes implements MapProperty {
	UNBOUND("unbound");

	private final String tag;

	CameraTypes(final String tag) {
		this.tag = tag;
	}

	@Override
	public String getTag() {
		return tag;
	}

	public static CameraTypes map(final String value) throws UnknownMapPropertyValueException {
		if ("".equals(value)) {
			throw new MissingMapPropertyException(
					"Map property '" + CustomMapPropertyTypes.CAMERA.getTag() + "' must not be empty!");
		}
		CameraTypes possibleValueOf = null;
		try {
			possibleValueOf = valueOf(value.toUpperCase());
		} catch (final IllegalArgumentException e) {
			throw new UnknownMapPropertyValueException(e);
		}
		return possibleValueOf;
	}
}
