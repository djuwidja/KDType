/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;

import org.springframework.stereotype.Service;

import com.djuwidja.networktype.NTObject;
/**
 * Serializer for {@link NTObject}. 
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class Serializer {
    /**
     * Serialize a {@link NTObject} into byte array.
     * @param data the {@link NTObject}.
     * @return byte array representation of the {@link NTObject}.
     * @throws SerializerException fails when unable to serialize {@link NTObject}.
     */
    public byte[] serialize(NTObject data) throws SerializerException {
    	SerializationFuncMapper mapper = SerializationFuncMapper.getInstance();
    	SerializeFunc func = mapper.getSerializer(data.getType());
    	
        SerializeResult sr = func.execute(data);
        return sr.getArray();  
    }
}
