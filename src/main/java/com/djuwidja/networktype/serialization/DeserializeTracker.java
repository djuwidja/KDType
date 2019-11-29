/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

/**
 * Tracks the progress of deserialization on the byte array.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
class DeserializeTracker {
    public int idx;
    
    public DeserializeTracker(){
        idx = 0;
    }
}
