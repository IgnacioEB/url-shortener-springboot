package com.ignacio.urlshortener2.DataTransferObject;

public class ErrorResponse {
    private String error;
    public ErrorResponse(String error){
        this.error=error;
    }
    public String getError(){
        return this.error;
    }


}
