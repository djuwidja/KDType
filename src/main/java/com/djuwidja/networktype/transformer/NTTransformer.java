/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.transformer;

import com.djuwidja.networktype.NTObject;

/**
 * Interface for transformers of {@link NTObject}s.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public interface NTTransformer {
    public NTObject transform(Object data) throws IllegalAccessException;
}
