package com.databaseCase.apirestdemo.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// responsable to handle exception response
@ControllerAdvice
public class VendorClassExceptionsHandler {

    //  Annotation basic ExceptionHandler
    @ExceptionHandler(value = {VendorClassNotFoundExceptions.class})
    public ResponseEntity<Object> handleVendorClassNotFound(VendorClassNotFoundExceptions vendorClassNotFoundExceptions) {
        VendorClassExceptions vendorClassExceptions = new VendorClassExceptions(vendorClassNotFoundExceptions.getMessage(), 
        vendorClassNotFoundExceptions.getCause(), HttpStatus.NOT_FOUND);

      return new ResponseEntity<>(vendorClassExceptions, HttpStatus.NOT_FOUND);
    }

}
