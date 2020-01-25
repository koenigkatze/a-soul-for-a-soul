package com.koenigkatze.asoulforasoul.maps.exceptions;

public final class MapObjectMissingAttributeException extends RuntimeException
{
	private static final long serialVersionUID = -3269879273975212018L;

	public MapObjectMissingAttributeException()
	{
		super();
	}

	public MapObjectMissingAttributeException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public MapObjectMissingAttributeException(final String message)
	{
		super(message);
	}

	public MapObjectMissingAttributeException(final Throwable throwable)
	{
		super(throwable);
	}
}
