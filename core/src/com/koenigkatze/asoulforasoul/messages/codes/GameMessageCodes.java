package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 0 - 999.
 * 
 * @author micha
 *
 */
public enum GameMessageCodes implements MessageCode
{
	LOAD_GAME(0), CHANGE_SCREEN(1), INPUT_LOCKED(2), INPUT_UNLOCKED(3)
	;

	public static final int MIN = 0;
	public static final int MAX = 999;
	
	private final int code;

	GameMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}
	
	public static GameMessageCodes getForCode(final int code) {
		for (final GameMessageCodes singleCode : values()) {
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
