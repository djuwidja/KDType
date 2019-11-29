package com.djuwidja.networktype.serialization;

import com.djuwidja.networktype.NTObject;
/**
 * Interface for Deserialization Func
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
interface DeserializeFunc {
	/**
	 * Execute deserialization
	 * @param byteArray the byte array
	 * @param tracker the {@link DeserializeTracker}
	 * @return the deserialized {@link NTObject}
	 * @throws DeserializerException fails when unable to deserialize byte array.
	 */
    public NTObject execute(byte[] byteArray, DeserializeTracker tracker) throws DeserializerException;
}
