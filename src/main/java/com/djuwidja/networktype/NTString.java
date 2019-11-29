/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTString represents String object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTString extends NTObject {
    private String mValue;
    
    public NTString(String v){
        super(NTObject.TYPE_STRING);
        mValue = v;
    }
    
    public String get(){
        return mValue;
    }
    
    public void set(String v){
        mValue = v;
    }
    
    @Override
    public String toDictKey(){
        return String.format("%s", mValue);
    }
    
    @Override
    public String toString(){
        return String.format("\"%s\"", mValue);
    }
    
    @Override
    public String toJsonString(){
        return toString();
    }
}
