package com.djuwidja.networktype.transformer;
/**
 * Exception thrown by {@link SupportedClassMapper}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class SupportedClassMapperException extends Exception {
	private static final long serialVersionUID = 1L;

	public SupportedClassMapperException() {
        super();
    }

    public SupportedClassMapperException(String message) {
        super(message);
    }

    public SupportedClassMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupportedClassMapperException(Throwable cause) {
        super(cause);
    }
}
