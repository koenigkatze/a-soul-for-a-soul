package com.koenigkatze.asoulforasoul.maps.properties.info;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.koenigkatze.asoulforasoul.maps.exceptions.MapObjectMissingAttributeException;

import com.badlogic.gdx.maps.MapProperties;

public class InfoObjectPropertiesMapper
{

	private final Set<InfoObjectMapProperties> mandatoryProperties;

	public InfoObjectPropertiesMapper()
	{
		super();
		mandatoryProperties = Stream.of(InfoObjectMapProperties.INFOTEXT).collect(Collectors.toSet());
	}
	
	public InfoObjectProperties loadProperties(final MapProperties mapProperties)
	{
		testProperties(mapProperties);
		final InfoObjectProperties infoProperties = new InfoObjectProperties();
		
		final String infoText = (String) mapProperties.get(InfoObjectMapProperties.INFOTEXT.getTag());
		infoProperties.setInfoText(infoText);

		return infoProperties;
	}

	private void testProperties(final MapProperties mapProperties)
	{
		for (final InfoObjectMapProperties singleProperty : mandatoryProperties) {
			if (!mapProperties.containsKey(singleProperty.getTag()))
				throw new MapObjectMissingAttributeException();
		}
		
	}
}
