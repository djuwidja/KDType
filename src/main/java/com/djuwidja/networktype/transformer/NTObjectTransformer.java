/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.transformer;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.djuwidja.networktype.NTDict;
/**
 * Wrapper service to transform an object into {@link NTDict}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
@Service
public class NTObjectTransformer { 
    public NTObjectTransformer(){

    }
    /**
     * Wrapper to transform object into NTObject
     * @param data object to be transformed.
     * @return transformed {@link NTDict}.
     * @throws NTObjectTransformerException fails when there is an illegal access exception when accessing data.
     */
    public NTDict transformObj(Object data) throws NTObjectTransformerException {
    	return NTObjectTransformerInternal.getInstance().transformObj(data);
    }
    /**
     * Wrapper to transform json into NTObject
     * @param data {@link JSONObject} to be transformed.
     * @return transformed {@link NTDict}.
     * @throws NTObjectTransformerException fails when unable to transform JSON.
     */
    public NTDict transformJson(JSONObject data) throws NTObjectTransformerException {
    	return NTObjectTransformerInternal.getInstance().transformJson(data);
    }
}
