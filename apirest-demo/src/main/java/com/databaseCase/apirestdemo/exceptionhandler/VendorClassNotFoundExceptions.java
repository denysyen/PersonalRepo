package com.databaseCase.apirestdemo.exceptionhandler;

public class VendorClassNotFoundExceptions extends RuntimeException {
    public VendorClassNotFoundExceptions(String message) {
        super(message);
    }

    public VendorClassNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
