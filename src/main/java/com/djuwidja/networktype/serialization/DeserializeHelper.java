package com.djuwidja.networktype.serialization;
/**
 * Helper class that provides function for byte manipulation during deserialization.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class DeserializeHelper {
	/**
	 * Obtain a serialization type from the byteArray and then increment the {@link DeserializeTracker}.
	 * @param byteArray the byte array.
	 * @param tracker the {@link DeserializeTracker}
	 * @return the {@link SerializationDataType}
	 */
	public static int getType(byte[] byteArray, DeserializeTracker tracker){
        int type = byteArray[tracker.idx];
        tracker.idx++;        
        return type;
    }
}
