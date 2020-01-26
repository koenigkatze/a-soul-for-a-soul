package com.koenigkatze.asoulforasoul.maps.exceptions;

public class UnknownMapPropertyValueException extends RuntimeException {

	private static final long serialVersionUID = -1336225606906252687L;

	public UnknownMapPropertyValueException()
	{
		super();
	}

	public UnknownMapPropertyValueException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public UnknownMapPropertyValueException(final String message)
	{
		super(message);
	}

	public UnknownMapPropertyValueException(final Throwable throwable)
	{
		super(throwable);
	}
}
