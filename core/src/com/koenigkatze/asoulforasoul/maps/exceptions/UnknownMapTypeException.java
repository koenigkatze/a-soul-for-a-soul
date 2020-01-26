package com.koenigkatze.asoulforasoul.maps.exceptions;

public class UnknownMapTypeException extends RuntimeException {

	private static final long serialVersionUID = 5837095500138410135L;

	public UnknownMapTypeException()
	{
		super();
	}

	public UnknownMapTypeException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public UnknownMapTypeException(final String message)
	{
		super(message);
	}

	public UnknownMapTypeException(final Throwable throwable)
	{
		super(throwable);
	}
}
