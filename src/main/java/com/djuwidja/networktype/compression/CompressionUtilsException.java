/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djuwidja.networktype.compression;

/**
 * Exceptions thrown by {@link CompressionUtils}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class CompressionUtilsException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompressionUtilsException() {
        super();
    }

    public CompressionUtilsException(String message) {
        super(message);
    }

    public CompressionUtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompressionUtilsException(Throwable cause) {
        super(cause);
    }
}
