package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTFloat;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a double or a {@link Double} into MQFloat.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQDoubleTransformer implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == double.class) {
			double value = (double) data;
	        return new NTFloat(value);
		} else if (data.getClass() == Double.class) {
			double value = ((Double) data).doubleValue();
			return new NTFloat(value);
		}
		
		return new NTNull();		
	}
}
