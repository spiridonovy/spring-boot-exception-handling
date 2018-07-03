package org.yspiridonov.sb.exception.handling.exception;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException() {
        super();
    }

    public SessionNotFoundException(String message) {
        super(message);
    }
}
