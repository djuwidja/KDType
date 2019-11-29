/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

import org.springframework.stereotype.Service;

import com.djuwidja.networktype.NTObject;
/**
 * Deserializer for MQType. 
 * @author kennethdjuwidja
 */
@Service
public class Deserializer {
    /**
     * Deserialize the byte array into MQType.
     * @param byteArray the byte array to be deserialized.
     * @return MQType results from the deserialization.
     * @throws DeserializerException fails when unable to deserialize the byte array.
     */
    public NTObject deserialize(byte[] byteArray) throws DeserializerException {
    	DeserializationFuncMapper deserializationCore = DeserializationFuncMapper.getInstance();
    	                
        DeserializeTracker tracker = new DeserializeTracker();
        int type = DeserializeHelper.getType(byteArray, tracker);
        DeserializeFunc func = deserializationCore.getDeserializer(type);
        NTObject result = func.execute(byteArray, tracker);

        return result;       
    }
}
