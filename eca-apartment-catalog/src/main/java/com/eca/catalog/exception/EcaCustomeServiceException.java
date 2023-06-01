package com.eca.catalog.exception;

public class EcaCustomeServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EcaCustomeServiceException() {
		super();
	}

	public EcaCustomeServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public EcaCustomeServiceException(String msg) {
		super(msg);
	}

	public EcaCustomeServiceException(Throwable cause) {
		super(cause);
	}

}
