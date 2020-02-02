package com.koenigkatze.asoulforasoul.maps.general;

import com.koenigkatze.asoulforasoul.game.world.CoordinateConverter;

public final class BlockedObjectProperties {

	private final float positionX;
	private final float positionY;
	private final float width;
	private final float height;

	private BlockedObjectProperties(final float positionX, final float positionY, final float width,
			final float height) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height = height;
	}

	public static BlockedObjectProperties of(
			final float positionX, 
			final float positionY, 
			final float width,
			final float height) {
		return new BlockedObjectProperties(
				positionX, 
				positionY,
				width, 
				height);
	}

	public static BlockedObjectProperties convertFrom(
			final float positionX, 
			final float positionY, 
			final float halfWidth,
			final float halfHeight) {
		return new BlockedObjectProperties(
				CoordinateConverter.convertToPhysicsScale(positionX),
				CoordinateConverter.convertToPhysicsScale(positionY), 
				CoordinateConverter.convertToPhysicsScale(halfWidth),
				CoordinateConverter.convertToPhysicsScale(halfHeight));
	}

	public float getPositionX() {
		return positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "BlockedObjectProperties [positionX=" + positionX + ", positionY=" + positionY + ", width=" + width
				+ ", height=" + height + "]";
	}

}
