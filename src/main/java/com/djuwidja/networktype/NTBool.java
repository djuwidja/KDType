/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTString represents the bool object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTBool extends NTObject {
    private boolean value;
    
    public NTBool(boolean v){
        super(NTObject.TYPE_BOOL);
        value = v;
    }
    
    public boolean get(){
        return value;
    }
    
    public void set(boolean v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        return String.format("%b", value);
    }
    
    @Override
    public String toJsonString(){
        return toString();
    }
}
