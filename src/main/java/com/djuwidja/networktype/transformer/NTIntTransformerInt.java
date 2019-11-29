package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a int or a {@link Integer} into {@link NTInt}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class NTIntTransformerInt implements NTTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == int.class) {
			int value = (int) data;
	        return new NTInt(value);
		} else if (data.getClass() == Integer.class) {
			int value = ((Integer) data).intValue();
			return new NTInt(value);
		}
		
		return new NTNull();
	}
}
