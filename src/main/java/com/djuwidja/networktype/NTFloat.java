/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTFloat represents the float and double objects.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTFloat extends NTObject {
    private double value;
    
    public NTFloat(float v){
        super(NTObject.TYPE_FLOAT);
        value = v;
    }
    
    public NTFloat(double v) {
    	super(NTObject.TYPE_FLOAT);
    	value = v;
    }
    
    public float getFloat() {
    	return (float) value;
    }
    
    public double getDouble(){
        return value;
    }
    
    public void set(int v){
        value = v;
    }
    
    @Override
    public String toDictKey(){
        return toString();
    }
    
    @Override
    public String toString(){
        return Double.toString(value);
    }
    
    @Override
    public String toJsonString(){
        return toString();
    }
}
