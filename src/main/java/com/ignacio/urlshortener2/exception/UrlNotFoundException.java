package com.ignacio.urlshortener2.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message){
         super(message);
    }

}
