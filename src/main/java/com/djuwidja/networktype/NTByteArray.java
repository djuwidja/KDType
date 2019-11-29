/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTString represents ByteArray object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTByteArray extends NTObject{
    private byte[] value;
    
    public NTByteArray(byte[] bytearray){
        super(NTObject.TYPE_BYTE_ARRAY);
        value = bytearray;
    }
    
    public byte[] get(){
        return value;
    }
    
    public void set(byte[] bytearray){
        value = bytearray;
    }
    
    @Override
    public String toDictKey(){
        return value.toString();
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
    
    @Override
    public String toJsonString(){
        return toString();
    }
}

