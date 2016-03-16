package se.coredev.jaxrs.service.exception;

public final class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 8258657021534155117L;

	public ServiceException(String message) {
		super(message);
	}

}
