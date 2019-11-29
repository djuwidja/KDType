package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTInt;
import com.djuwidja.networktype.NTNull;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a short or a {@link Short} into {@link NTInt}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class NTIntTransformerShort implements NTTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		if (data.getClass() == short.class) {
			short value = (short) data;
	        return new NTInt(value);
		} else if (data.getClass() == Short.class) {
			short value = ((Short) data).shortValue();
			return new NTInt(value);
		}
		
		return new NTNull();
	}
}
