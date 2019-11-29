package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTBool;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a bool or a {@link Boolean} into {@link NTBool}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class NTBoolTransformer implements NTTransformer{
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == boolean.class) {
			boolean value = (boolean) data;
	        return new NTBool(value);
		} else if (data.getClass() == Boolean.class) {
			boolean value = ((Boolean) data).booleanValue();
			return new NTBool(value);
		}
		
		return new NTNull();		
	}
}
