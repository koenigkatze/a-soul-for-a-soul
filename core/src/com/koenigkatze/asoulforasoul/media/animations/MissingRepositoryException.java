package com.koenigkatze.asoulforasoul.media.animations;

public final class MissingRepositoryException extends RuntimeException
{
	private static final long serialVersionUID = 1320110359027074300L;

	public MissingRepositoryException()
	{
		super();
	}

	public MissingRepositoryException(final String message, final Throwable throwable)
	{
		super(message, throwable);
	}

	public MissingRepositoryException(final String message)
	{
		super(message);
	}

	public MissingRepositoryException(final Throwable throwable)
	{
		super(throwable);
	}

}
