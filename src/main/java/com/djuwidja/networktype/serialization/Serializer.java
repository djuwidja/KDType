/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

import org.springframework.stereotype.Service;

import com.djuwidja.networktype.NTObject;
/**
 * Serializer for MQType. 
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class Serializer {
    /**
     * Serialize a MQType into byte array.
     * @param data the MQType.
     * @return byte array representation of the MQType.
     * @throws SerializerException fails when unable to serialize MQType.
     */
    public byte[] serialize(NTObject data) throws SerializerException {
    	SerializationFuncMapper mapper = SerializationFuncMapper.getInstance();
    	SerializeFunc func = mapper.getSerializer(data.getType());
    	
        SerializeResult sr = func.execute(data);
        return sr.getArray();  
    }
}
