/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype;
/**
 * Base class for all NTObject
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public abstract class NTObject extends java.lang.Object {
    public static final int TYPE_BOOL = 1;
    public static final int TYPE_INT = 2;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_STRING = 4;
    public static final int TYPE_BYTE_ARRAY = 5;
    public static final int TYPE_DATETIME = 50;
    public static final int TYPE_LIST = 100;
    public static final int TYPE_DICT = 101;
    public static final int TYPE_NULL = 999;
    
    private int type;
    /**
     * Constructor, should only be used by child class.
     * @param type the type of this NTObject.
     */
    public NTObject(int type){
        this.type = type;
    }
    /**
     * Get type of this NTObject,
     * @return type of this NTObject.
     */
    public int getType(){
        return type;
    }
    /**
     * Get the string of the key in map for NTDict.
     * @return dictionary key for this NTObject.
     */
    public String toDictKey(){
        return String.format("Type(%d)", type);
    }
    /**
     * toString().
     * @return String
     */
    @Override
    public String toString(){
        return String.format("Type(%d)", type);
    }
    /**
     * Get the JSON String representation of this NTObject.
     * @return JSON String.
     */
    public abstract String toJsonString();
}
