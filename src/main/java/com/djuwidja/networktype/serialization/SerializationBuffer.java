/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

import java.nio.ByteBuffer;
/**
 * Elastic buffer to write large byte array for serialization. Mostly for serializing MQDict.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class SerializationBuffer {
    private static final int CAPACITY_SIZE = 1024;
    private ByteBuffer bf;
    
    private int capacity;
    private int currSize;
    
    public SerializationBuffer(){
        currSize = 0;
        capacity = 1024;
        bf = ByteBuffer.allocate(capacity);
    }
    /**
     * Write bytes into buffer.
     * @param bytes
     */
    public void writeBytes(byte[] bytes){
        currSize += bytes.length;
        if (currSize > capacity){
            int div = currSize / CAPACITY_SIZE;
            int mod = currSize % CAPACITY_SIZE;
            
            capacity = div * CAPACITY_SIZE + (mod > 0? CAPACITY_SIZE : 0);
            ByteBuffer newBf = ByteBuffer.allocate(capacity);
            int length = bf.position();
            bf.rewind();
            byte[] cloneArray = new byte[length];
            bf.get(cloneArray, 0, length);
            newBf.put(cloneArray);
            bf = newBf;
        }
        
        bf.put(bytes);
    }
    /**
     * Get a trimmed byte array from the buffer.
     * @return the trimemd byte array.
     */
    public byte[] getArray(){
        byte[] resultArr = new byte[currSize];
        byte[] bfArr = bf.array();
        System.arraycopy(bfArr, 0, resultArr, 0, currSize);
        return resultArr;
    }
}
