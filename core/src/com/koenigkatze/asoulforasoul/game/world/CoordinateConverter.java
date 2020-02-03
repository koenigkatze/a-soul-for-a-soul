package com.koenigkatze.asoulforasoul.game.world;

import com.koenigkatze.asoulforasoul.constants.ConversionConstants;

public final class CoordinateConverter {

	public static float convertToPhysicsScale(final float value) {
		return value / ConversionConstants.PIXELS_TO_METERS;
	}

	public static float convertFromPhysicsScale(final float value) {
		return value * ConversionConstants.PIXELS_TO_METERS;
	}

}
