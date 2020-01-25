package com.koenigkatze.asoulforasoul.maps.exceptions;

public final class UnknownMapLayerException extends RuntimeException
{
	private static final long serialVersionUID = -6829563627637254829L;

	public UnknownMapLayerException()
	{
		super();
	}

	public UnknownMapLayerException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public UnknownMapLayerException(final String message)
	{
		super(message);
	}

	public UnknownMapLayerException(final Throwable throwable)
	{
		super(throwable);
	}

}
