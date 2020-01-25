package com.koenigkatze.asoulforasoul.messages.utils;

import com.koenigkatze.asoulforasoul.messages.codes.MessageCode;

public class Messages
{

	public static void publish(MessageCode code)
	{
		MessageBuilder.forType(code.get()).dispatch();
	}
	
	public static void publish(MessageCode code, Object extraInfo)
	{
		MessageBuilder.forType(code.get()).withExtraInfo(extraInfo).dispatch();
	}



}
