package com.koenigkatze.asoulforasoul.ui;

public final class UiMessageData
{
	public final static class UiDataBuilder
	{
		private String text;

		private UiDataBuilder()
		{
		}

		public UiDataBuilder withText(final String text)
		{
			this.text = text;
			return this;
		}

		public UiMessageData build()
		{
			return new UiMessageData(text);
		}

	}

	private final String text;

	private UiMessageData(final String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public static UiDataBuilder builder()
	{
		return new UiDataBuilder();
	}

	@Override
	public String toString()
	{
		return "UiMessageData [text=" + text + "]";
	}
	
	

}
