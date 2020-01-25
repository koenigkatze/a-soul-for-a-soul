package com.koenigkatze.asoulforasoul.maps.exceptions;

public final class UnknownMapObjectTypeException extends RuntimeException
{
	private static final long serialVersionUID = 2415846485881172010L;

	public UnknownMapObjectTypeException()
	{
		super();
	}

	public UnknownMapObjectTypeException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public UnknownMapObjectTypeException(final String message)
	{
		super(message);
	}

	public UnknownMapObjectTypeException(final Throwable throwable)
	{
		super(throwable);
	}

}
