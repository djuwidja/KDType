/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTNull represents null.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTNull extends NTObject {
    
    public NTNull(){
        super(NTObject.TYPE_NULL);
    }
    
    @Override
    public String toDictKey(){
        return "null";
    }
    
    @Override
    public String toString(){
        return "null";
    }
    
    @Override
    public String toJsonString(){
        return "null";
    }
    
    @Override
    public boolean equals(NTObject obj) {
    	return super.equals(obj);
    }
}
