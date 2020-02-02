package com.koenigkatze.asoulforasoul.maps.general;

import java.util.Collection;
import java.util.stream.Collectors;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.koenigkatze.asoulforasoul.messages.codes.EntityMessageCodes;
import com.koenigkatze.asoulforasoul.messages.utils.Messages;

public final class BlockedMapPartsCreator {

	public static void createFromCollection(final Collection<RectangleMapObject> blockedMapObjects) {
		final BlockedObjectBulkProperties collectedProperties = new BlockedObjectBulkProperties(
				blockedMapObjects.stream()
				.map(RectangleMapObject::getRectangle)
				.map(rectangle -> BlockedObjectProperties.convertFrom((rectangle.x + (rectangle.width / 2)),
						(rectangle.y + (rectangle.height / 2)), rectangle.width, rectangle.height))
				.collect(Collectors.toList()));

		Messages.publish(EntityMessageCodes.BULK_CREATE_BLOCKED_OBJECT, collectedProperties);
	}

}
