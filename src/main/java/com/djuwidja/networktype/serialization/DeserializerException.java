/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.serialization;
/**
 * Exception thrown by Deserializer.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class DeserializerException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeserializerException() {
        super();
    }

    public DeserializerException(String message) {
        super(message);
    }

    public DeserializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeserializerException(Throwable cause) {
        super(cause);
    }
}
