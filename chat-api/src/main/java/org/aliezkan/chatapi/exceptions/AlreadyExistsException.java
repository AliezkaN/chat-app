package org.aliezkan.chatapi.exceptions;

public abstract class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
