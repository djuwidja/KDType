package com.djuwidja.datautils;
/**
 * Exceptions thrown by {@link TransferDataService}
 * @author kennethdjuwidja
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class TransferDataServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public TransferDataServiceException() {
        super();
    }

    public TransferDataServiceException(String message) {
        super(message);
    }

    public TransferDataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferDataServiceException(Throwable cause) {
        super(cause);
    }
}