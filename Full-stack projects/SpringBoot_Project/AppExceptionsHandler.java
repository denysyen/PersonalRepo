package com.example;


import  org.springframework.web.bind.annotation.ControllerAdvice;
import  org.springframework.web.bind.annotation.AppExceptionsHandler;
import  org.springframework.http.ResponseEntity;
import  org.springframework.http.HttpStatus;
import  org.springframework.http.HttpHeaders;
import  org.springframework.web.context.request.WebRequest;
import  org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // to listen the HTTP methods 
public class AppExceptionsHandler {

    @ExceptionsHandler(value = {Exception.class})
    public ResponseEntity<Objetc> handlerArryException(Exception ex, WebRequest request) {

        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null) errorMessage = ex.toString();
        
        ErrorMassage  errorMessage = new ErrorMassage(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
