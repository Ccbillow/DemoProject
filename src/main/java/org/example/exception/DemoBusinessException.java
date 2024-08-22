package org.example.exception;

public class DemoBusinessException extends RuntimeException{

    public DemoBusinessException() {
        super();
    }

    public DemoBusinessException(String message) {
        super(message);
    }

    public DemoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoBusinessException(Throwable cause) {
        super(cause);
    }
}
