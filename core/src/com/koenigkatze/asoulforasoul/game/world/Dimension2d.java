package com.koenigkatze.asoulforasoul.game.world;

public final class Dimension2d
{
	private final int width;
	private final int height;

	private Dimension2d(final int width, final int height)
	{
		super();
		this.width = width;
		this.height = height;
	}

	public static Dimension2d of(final int width, final int height)
	{
		return new Dimension2d(width, height);
	}
	
	public static Dimension2d of(final float width, final float height)
	{
		return new Dimension2d((int) width, (int) height);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	@Override
	public String toString()
	{
		return "Dimension2d [width=" + width + ", height=" + height + "]";
	}



}
