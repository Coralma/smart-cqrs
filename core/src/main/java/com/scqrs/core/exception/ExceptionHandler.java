package com.scqrs.core.exception;

public class ExceptionHandler {

    public static RuntimeException newRuntimeException(Class<?> cls, String message) {
        // add log operation.
        return new RuntimeException(message);
    }
}
