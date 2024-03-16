package com.databaseCase.apirestdemo.exceptionhandler;

import org.springframework.http.HttpStatus;

public class VendorClassExceptions {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus heHttpStatus;


    public VendorClassExceptions(String message, Throwable throwable, HttpStatus heHttpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.heHttpStatus = heHttpStatus;
    }


    public String getMessage() {
        return message;
    }


    public Throwable getThrowable() {
        return throwable;
    }


    public HttpStatus getHeHttpStatus() {
        return heHttpStatus;
    }
    
    
    

    

    

}
