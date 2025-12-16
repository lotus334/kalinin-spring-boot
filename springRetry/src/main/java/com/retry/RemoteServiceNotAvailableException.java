package com.retry;

public class RemoteServiceNotAvailableException extends RuntimeException {
    public RemoteServiceNotAvailableException(String message) {
        super("Remote service is not available: " + message);
    }
}
