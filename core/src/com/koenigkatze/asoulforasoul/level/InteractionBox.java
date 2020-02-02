package com.koenigkatze.asoulforasoul.level;

import com.koenigkatze.asoulforasoul.game.world.Coordinate2d;
import com.koenigkatze.asoulforasoul.game.world.Dimension2d;

public final class InteractionBox
{
	private final Coordinate2d position;
	private final Dimension2d dimension;
	private boolean alreadyInBounds;

	public InteractionBox(Coordinate2d position, Dimension2d dimension)
	{
		this.position = position;
		this.dimension = dimension;
	}

	public static InteractionBox of(Coordinate2d position, Dimension2d dimension)
	{
		return new InteractionBox(position, dimension);
	}

	public boolean isInBounds(int x, int y)
	{
		final float boxStartX = position.getX();
		final float boxStartY = position.getY();
		final boolean biggerOrEqualsThanMinPoint = x >= boxStartX && y >= boxStartY;
		final boolean biggerOrEqualsThanMaxPoint = x <= boxStartX + dimension.getWidth()
				&& y <= boxStartY + dimension.getHeight();
		return biggerOrEqualsThanMinPoint && biggerOrEqualsThanMaxPoint;
	}
	
	public boolean isAlreadyInBounds()
	{
		return alreadyInBounds;
	}
	
	public void setAlreadyInBounds(boolean value)
	{
		alreadyInBounds = value;
	}

	public Coordinate2d getPosition()
	{
		return position;
	}

	public Dimension2d getDimension()
	{
		return dimension;
	}

	@Override
	public String toString()
	{
		return "InteractionBox [position=" + position + ", dimension=" + dimension + "]";
	}




}
