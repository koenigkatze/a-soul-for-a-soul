package com.koenigkatze.asoulforasoul.game.world;

import com.koenigkatze.asoulforasoul.constants.ConversionConstants;

public class CoordinateConverter {

	public static float convertToPhysicsScale(float value) {
		return value / ConversionConstants.PIXELS_TO_METERS;
	}
	
}
