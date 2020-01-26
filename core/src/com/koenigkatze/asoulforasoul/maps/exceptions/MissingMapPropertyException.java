package com.koenigkatze.asoulforasoul.maps.exceptions;

public final class MissingMapPropertyException extends RuntimeException
{
	private static final long serialVersionUID = -3269879273975212018L;

	public MissingMapPropertyException()
	{
		super();
	}

	public MissingMapPropertyException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public MissingMapPropertyException(final String message)
	{
		super(message);
	}

	public MissingMapPropertyException(final Throwable throwable)
	{
		super(throwable);
	}
}
