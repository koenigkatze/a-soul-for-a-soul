package com.koenigkatze.asoulforasoul.level;

public final class ShutdownMessage {

	private final String message;

	public ShutdownMessage(final String message) {
		this.message = message;
	}

	public static ShutdownMessage of(final String message) {
		return new ShutdownMessage(message);
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ShutdownMessage [message=" + message + "]";
	}

}
