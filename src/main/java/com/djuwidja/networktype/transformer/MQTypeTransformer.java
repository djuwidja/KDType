/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTObject;

/**
 * Interface for MQTypeTransformers.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public interface MQTypeTransformer {
    public NTObject transform(Object data) throws IllegalAccessException;
}
