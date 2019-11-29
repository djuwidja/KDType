/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

/**
 * Type definition for data types during serialization and deserialization.
 * @author kennethdjuwidja
 */
class SerializationDataType {
    public static final int TYPE_NULL = -1;
    public static final int TYPE_BOOL = 1;
    public static final int TYPE_INT = 2;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_STRING = 4;
    public static final int TYPE_BYTE_ARRAY = 5;
    public static final int TYPE_DATETIME = 50;
    public static final int TYPE_LIST = 100;
    public static final int TYPE_DICT = 101;    
}
