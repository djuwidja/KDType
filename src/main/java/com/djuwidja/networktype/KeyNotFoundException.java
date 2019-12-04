package com.djuwidja.networktype;

public class KeyNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public KeyNotFoundException() {
        super();
    }

    public KeyNotFoundException(String message) {
        super(message);
    }

    public KeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyNotFoundException(Throwable cause) {
        super(cause);
    }
}
