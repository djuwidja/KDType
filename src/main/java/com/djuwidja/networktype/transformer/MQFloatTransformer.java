package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTFloat;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a float or a {@link Float} into MQFloat.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQFloatTransformer implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == float.class) {
			float value = (float) data;
	        return new NTFloat(value);
		} else if (data.getClass() == Float.class) {
			float value = ((Float) data).floatValue();
			return new NTFloat(value);
		}
		
		return new NTNull();		
	}
}
