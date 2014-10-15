package com.redhat.masvida.rcm.servicelocator;

public class ServiceLocatorException extends Exception{
   
	private static final long serialVersionUID = 1L;
	protected Throwable causa;

    public ServiceLocatorException() {
        super();
    }
    public ServiceLocatorException(Throwable causa) {
        super(causa);
    }

    public ServiceLocatorException(String message) {
        super(message);
    }

    public ServiceLocatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
