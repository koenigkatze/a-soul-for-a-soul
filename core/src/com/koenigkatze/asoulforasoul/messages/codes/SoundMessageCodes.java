package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 2000 - 2999.
 * 
 * @author micha
 *
 */
public enum SoundMessageCodes implements MessageCode
{
	
	
	
	;
	
	public static final int MIN = 2000;
	public static final int MAX = 2999;
	
	private final int code;

	SoundMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}
	
	public static SoundMessageCodes getForCode(final int code) {
		for (final SoundMessageCodes singleCode : values()) {
			if (singleCode.code == code) {
				return singleCode;
			}
		}
		throw new IllegalArgumentException("No message type found for provided code: "+code);
	}
	
	public static boolean isInRange(final int code) {
		return code >= MIN && code <= MAX;
	}
}
