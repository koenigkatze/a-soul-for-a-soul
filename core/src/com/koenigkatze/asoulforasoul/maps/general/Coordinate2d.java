package com.koenigkatze.asoulforasoul.maps.general;

public final class Coordinate2d
{
	private final float x;
	private final float y;

	private Coordinate2d(final float x, final float y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	public static Coordinate2d of(final float x, final float y)
	{
		return new Coordinate2d(x, y);
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	@Override
	public String toString()
	{
		return "Coordinate2d [x=" + x + ", y=" + y + "]";
	}

	public Coordinate2d traverse(final int xAmount, final int yAmount)
	{
		return new Coordinate2d(x+xAmount, y+yAmount);
	}

}
