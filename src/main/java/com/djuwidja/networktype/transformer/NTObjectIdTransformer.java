package com.djuwidja.networktype.transformer;

import org.bson.types.ObjectId;

import com.djuwidja.networktype.NTObject;
import com.djuwidja.networktype.NTString;
/**
 * Transform a {@link ObjectId} into {@link NTString}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class NTObjectIdTransformer implements NTTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		ObjectId objectId = (ObjectId) data;
		String value = objectId.toString();
        return new NTString(value);
	}
}
