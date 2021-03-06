package com.koenigkatze.asoulforasoul.messages.codes;

/**
 * Messagecode range: 3000 - 3999.
 * 
 * @author micha
 *
 */
public enum EntityMessageCodes implements MessageCode
{
	// Requesting creation
	CREATE_PLAYER_ENTITY(3000), 
	CREATE_NPC_ENTITY(3001), 
	CREATE_PASSIVE_MAP_OBJECT(3002), 
	CREATE_INFO_OBJECT(3003), 
	CREATE_BLOCKED_OBJECT(3004),

	// Requesting bulk creation
	BULK_CREATE_BLOCKED_OBJECT(3104),  
	
	// Entity created
	PLAYER_ENTITY_CREATED(3200), 
	NPC_ENTITY_CREATED(3201), 
	PASSIVE_MAP_OBJECT_CREATED(3202),
	INFO_OBJECT_CREATED(3203),
	
	// Interaction
	PLAYER_INTERACT(3300), 
	
	// Mouse overs
	MOUSE_OVER_RELEASED(3400),
	MOUSE_OVER_INFO_OBJECT(3401), 
	
	;
	
	public static final int MIN = 3000;
	public static final int MAX = 3999;
	
	private final int code;

	EntityMessageCodes(final int code)
	{
		this.code = code;
	}

	@Override
	public int get()
	{
		return code;
	}
	
	public static EntityMessageCodes getForCode(final int code) {
		for (final EntityMessageCodes singleCode : values()) {
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
