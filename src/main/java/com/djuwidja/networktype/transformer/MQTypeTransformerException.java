package com.djuwidja.networktype.transformer;
/**
 * Exception thrown by MQTypeTransformer.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class MQTypeTransformerException extends Exception {
	private static final long serialVersionUID = 1L;

	public MQTypeTransformerException() {
        super();
    }

    public MQTypeTransformerException(String message) {
        super(message);
    }

    public MQTypeTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MQTypeTransformerException(Throwable cause) {
        super(cause);
    }
}
