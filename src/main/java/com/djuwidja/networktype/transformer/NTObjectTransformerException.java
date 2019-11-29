package com.djuwidja.networktype.transformer;
/**
 * Exception thrown by {@link NTObjectTransformer}.
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 */
public class NTObjectTransformerException extends Exception {
	private static final long serialVersionUID = 1L;

	public NTObjectTransformerException() {
        super();
    }

    public NTObjectTransformerException(String message) {
        super(message);
    }

    public NTObjectTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NTObjectTransformerException(Throwable cause) {
        super(cause);
    }
}
