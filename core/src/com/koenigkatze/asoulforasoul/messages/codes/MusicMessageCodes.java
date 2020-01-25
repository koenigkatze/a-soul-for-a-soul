package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 4000 - 4999.
 * 
 * @author micha
 *
 */
public enum MusicMessageCodes implements MessageCode
{
	PLAY(4000), STOP(4001), MUTE(4002), DECREASE_VOLUME(4003), INCREASE_VOLUME(4004), TOGGLE_MUTE_UNMUTE(4005)
	
	
	;
	
	public static final int MIN = 4000;
	public static final int MAX = 4999;
	
	private final int code;

	MusicMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}
	
	public static MusicMessageCodes getForCode(final int code) {
		for (final MusicMessageCodes singleCode : values()) {
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
