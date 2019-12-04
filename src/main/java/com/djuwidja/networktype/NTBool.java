/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * NTBool represents the bool object.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTBool extends NTObject {
    private boolean value;
    /**
     * Construct a new NTBool with false
     */
    public NTBool() {
    	super(NTObject.TYPE_BOOL);
    	value = false;
    }
    /**
     * Construct a new NTBool
     * @param v the default value
     */
    public NTBool(boolean v){
        super(NTObject.TYPE_BOOL);
        value = v;
    }
    /**
     * Returns the bool of this NTBool
     * @return bool
     */
    public boolean get(){
        return value;
    }
    /**
     * Sets the bool of this NTBool
     * @param v the new bool value
     */
    public void set(boolean v){
        value = v;
    }
    /**
     *
     */
    @Override
    public String toDictKey(){
        return toString();
    }
    /**
	 *
     */
    @Override
    public String toString(){
        return String.format("%b", value);
    }
    /**
     *
     */
    @Override
    public String toJsonString(){
        return toString();
    }
    /**
     *
     */
    @Override
    public boolean equals(NTObject obj) {
    	if (!super.equals(obj)) {
    		return false;
    	}

    	return value == ((NTBool) obj).get();
    }
}
