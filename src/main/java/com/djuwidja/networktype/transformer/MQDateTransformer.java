package com.djuwidja.networktype.transformer;

import java.util.Date;

import com.djuwidja.networktype.NTDate;
import com.djuwidja.networktype.NTObject;
/**
 * Transform a Date into MQDate.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class MQDateTransformer implements MQTypeTransformer {
	@Override
	public NTObject transform(Object data) throws IllegalAccessException {
		Date value = (Date) data;
        return new NTDate(value);
	}
}
