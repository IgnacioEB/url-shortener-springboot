package com.ignacio.urlshortener2.DataTransferObject;

public class ErrorResponse {
    private String message;
    private int status;
    private String error;


    public ErrorResponse(String message, int status, String error){
        this.message=message;
        this.status=status;
        this.error=error;
    }
    public String getMessage(){
            return this.message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
