package com.koenigkatze.asoulforasoul.maps.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class BlockedObjectBulkProperties {

	private final Collection<BlockedObjectProperties> bulkProperties;

	public BlockedObjectBulkProperties(final Collection<BlockedObjectProperties> collectedProperties) {
		this.bulkProperties = new ArrayList<>(collectedProperties);
	}

	public Collection<BlockedObjectProperties> getCollectionOfProperties() {
		return Collections.unmodifiableCollection(bulkProperties);
	}

	@Override
	public String toString() {
		return "BlockedObjectBulkProperties [bulkProperties=" + bulkProperties + "]";
	}
	
}
