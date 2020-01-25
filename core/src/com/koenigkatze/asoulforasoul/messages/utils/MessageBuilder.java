package com.koenigkatze.asoulforasoul.messages.utils;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegraph;

public final class MessageBuilder
{
	private final int messageType;
	private int delay;
	private Telegraph sender;
	private Telegraph receiver;
	private Object extraInfo;
	private boolean needsReturnReceipt;

	public MessageBuilder(final int messageType)
	{
		this.messageType = messageType;
	}

	public static MessageBuilder forType(final int messageType)
	{
		return new MessageBuilder(messageType);
	}

	public MessageBuilder withDelay(final int amount)
	{
		this.delay = amount;
		return this;
	}

	public MessageBuilder withSender(final Telegraph sender)
	{
		this.sender = sender;
		return this;
	}

	public MessageBuilder withReceiver(final Telegraph receiver)
	{
		this.receiver = receiver;
		return this;
	}

	public MessageBuilder withExtraInfo(final Object extraInfo)
	{
		this.extraInfo = extraInfo;
		return this;
	}

	public MessageBuilder withNeedsReturnReceipt(final boolean needsReturnReceipt)
	{
		this.needsReturnReceipt = needsReturnReceipt;
		return this;
	}

	public void dispatch()
	{
		MessageManager.getInstance().dispatchMessage(delay, sender, receiver, messageType, extraInfo,
				needsReturnReceipt);
	}

}
