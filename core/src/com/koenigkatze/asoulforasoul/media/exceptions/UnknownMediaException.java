package com.koenigkatze.asoulforasoul.media.exceptions;

public class UnknownMediaException extends RuntimeException
{
	private static final long serialVersionUID = -7819729750550961849L;

	public UnknownMediaException()
	{
		super();
	}

	public UnknownMediaException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public UnknownMediaException(final String message)
	{
		super(message);
	}

	public UnknownMediaException(final Throwable throwable)
	{
		super(throwable);
	}
}
