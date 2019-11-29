package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a long or a {@link Long} into MQInt.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQIntTransformerLong implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == long.class) {
			long value = (long) data;
	        return new NTInt(value);
		} else if (data.getClass() == Long.class) {
			long value = ((Long) data).longValue();
			return new NTInt(value);
		}
		
		return new NTNull();
	}
}
