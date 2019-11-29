/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

/**
 * The result of serialization. It is composed by 1 byte {@link SerializationDataType} and the a byteArray of contents.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class SerializeResult {
    private byte mType;
    private byte[] mBytes;
    /**
     * Constructor
     * @param type byte representation of {@link SerializationDataType}.
     * @param bytes the content of data.
     */
    public SerializeResult(byte type, byte[] bytes){
        mType = type;
        mBytes = bytes;
    }
    /**
     * 
     * @return the byte representation of {@link SerializationDataType}.
     */
    public byte getType(){
        return mType;
    }
    /**
     * 
     * @return the content of data.
     */
    public byte[] getBytes(){
        return mBytes;
    }
    /**
     * Construct a byte array representation of this object by placing the type at the first index followed the contents.
     * Also trimmed the byte array.
     * @return the byte array representation of this object.
     */
    public byte[] getArray(){
        byte[] result = new byte[mBytes.length + 1];
        result[0] = mType;
        System.arraycopy(mBytes, 0, result, 1, mBytes.length);
        return result;
    }
}
