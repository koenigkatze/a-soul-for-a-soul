package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 1000 - 1999.
 * 
 * @author micha
 *
 */
public enum LevelMessageCodes implements MessageCode
{
	LEVEL_DATA_LOADING(1000), LEVEL_DATA_LOADED(1001),

	;

	public static final int MIN = 1000;
	public static final int MAX = 1999;

	private final int code;

	LevelMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}

	public static LevelMessageCodes getForCode(final int code)
	{
		for (final LevelMessageCodes singleCode : values())
		{
			if (singleCode.code == code)
			{
				return singleCode;
			}
		}
		throw new IllegalArgumentException("No message type found for provided code: " + code);
	}

	public static boolean isInRange(final int code)
	{
		return code >= MIN && code <= MAX;
	}
}
