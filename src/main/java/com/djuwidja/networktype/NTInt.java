/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;

import com.djuwidja.networktype.NTObject;
/**
 * NTString represents the short, int and long objects.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTInt extends NTObject {
    private long value;
    
    public NTInt(short v ){
       super(NTObject.TYPE_INT);
       value = v; 
    }
    
    public NTInt(int v){
        super(NTObject.TYPE_INT);
        value = v;
    }
    
    public NTInt(long v){
        super(NTObject.TYPE_INT);
        value = v;
    }
    
    public long getLong(){
        return value;
    }
    
    public int getInt(){
        return (int) value;
    }
    
    public short getShort(){
        return (short) value;
    }
    
    public void set(long v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        return String.format("%d", value);
    }
    
    @Override
    public String toJsonString(){
        return toString();
    }
}
