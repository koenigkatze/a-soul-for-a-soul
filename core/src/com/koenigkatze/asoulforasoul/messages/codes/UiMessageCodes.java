package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 5000 - 5999.
 * 
 * @author micha
 *
 */
public enum UiMessageCodes implements MessageCode
{
	DISPLAY_MESSAGE_BOX(5000)
	
	
	
	
	
	;
	
	public static final int MIN = 5000;
	public static final int MAX = 5999;
	
	private final int code;

	UiMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}
	
	public static UiMessageCodes getForCode(final int code) {
		for (final UiMessageCodes singleCode : values()) {
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
