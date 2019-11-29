package com.djuwidja.networktype.serialization;

import com.djuwidja.networktype.NTObject;
/**
 * Interface for Serialization Func
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
interface SerializeFunc {
	/**
	 * Execute the serialization.
	 * @param data the data to be serialized.
	 * @return {@link SerializeResult}
	 * @throws SerializerException fails to serialization the data.
	 */
	public SerializeResult execute(NTObject data) throws SerializerException;
}
