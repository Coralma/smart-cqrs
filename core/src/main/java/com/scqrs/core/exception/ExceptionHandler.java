package com.scqrs.core.exception;

public class ExceptionHandler {

    public static RuntimeException newRuntimeException(Class<?> cls, String message) {
        // add log opertion.
        return new RuntimeException(message);
    }
}
