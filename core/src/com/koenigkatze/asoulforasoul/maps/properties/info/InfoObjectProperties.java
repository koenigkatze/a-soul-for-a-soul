package com.koenigkatze.asoulforasoul.maps.properties.info;

import com.koenigkatze.asoulforasoul.maps.general.Coordinate2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InfoObjectProperties
{

	private Coordinate2d position;
	private TextureRegion texture;
	private String infoText;

	public InfoObjectProperties(Coordinate2d position, TextureRegion texture, String infoText)
	{
		this.position = position;
		this.texture = texture;
		this.infoText = infoText;
	}

	public InfoObjectProperties()
	{
	}

	public Coordinate2d getPosition()
	{
		return position;
	}

	public void setPosition(Coordinate2d position)
	{
		this.position = position;
	}

	public TextureRegion getTexture()
	{
		return texture;
	}

	public void setTexture(TextureRegion texture)
	{
		this.texture = texture;
	}

	public String getInfoText()
	{
		return infoText;
	}

	public void setInfoText(String infoText)
	{
		this.infoText = infoText;
	}

	@Override
	public String toString()
	{
		return "InfoObjectProperties [position=" + position + ", texture=" + texture + ", infoText=" + infoText + "]";
	}

}
