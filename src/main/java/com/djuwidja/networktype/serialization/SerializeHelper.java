package com.djuwidja.networktype.serialization;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
/**
 * Helper class that provides function for byte manipulation during serialization.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class SerializeHelper {
	/**
	 * Creates a byte array for serialization with the length val at the first index followed by the contents of val.
	 * Invoke {@link SerializeHelper#getByteArrayInt(long)} to obtain the byte of the length. 
	 * Invoke {@link SerializeHelper#mergeByteArray(byte[], byte[])} to merge byte arrays.
	 * @param val byte array to be transformed.
	 * @return the transformed byte array.
	 */
	public static byte[] getByteArrayBytes(byte[] val){
        byte[] lenByte = getByteArrayInt(val.length);
        return mergeByteArray(lenByte, val);
    }
	/**
	 * Creates a string for serialization with the length of string at the first index followed by the byte array of string.
	 * Invoke {@link SerializeHelper#getByteArrayBytes(byte[])}.
	 * Invoke {@link SerializeHelper#mergeByteArray(byte[], byte[])} to merge byte arrays.
	 * @param val the string to be transformed.
	 * @return the transformed byte array.
	 */
	public static byte[] getByteArrayStr(String val){
        byte[] byteVal = val.getBytes(StandardCharsets.UTF_8);
        int byteLen = byteVal.length;
        byte[] lenByte = getByteArrayInt(byteLen);
        return mergeByteArray(lenByte, byteVal);
    }
	/**
	 * Create a short, an integer or a long base on the value of the input.
	 * @param val the long to be transformed.
	 * @return the transformed byte array.
	 */
	public static byte[] getByteArrayInt(long val){
        ByteBuffer bf = null;
        if (val >= Short.MIN_VALUE && val <= Short.MAX_VALUE){
            bf = ByteBuffer.allocate(3);
            bf.put((byte) 2);
            bf.putShort((short) val);
            
        } else if (val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE){
            bf = ByteBuffer.allocate(5);
            bf.put((byte) 4);
            bf.putInt((int) val);
        } else {
            bf = ByteBuffer.allocate(9);
            bf.put((byte) 8);
            bf.putLong(val);
        }

        return bf.array();
    }
	/**
	 * merge the two byte arrays
	 * @param one byte array 1
	 * @param two byte array 2
	 * @return the merged byte array.
	 */
	public static byte[] mergeByteArray(byte[] one, byte[] two){
        byte[] result = new byte[one.length + two.length];
        System.arraycopy(one, 0, result, 0, one.length);
        System.arraycopy(two, 0, result, one.length, two.length);
        
        return result;
    }
}
