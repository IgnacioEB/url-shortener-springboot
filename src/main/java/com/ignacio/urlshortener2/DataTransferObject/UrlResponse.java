package com.ignacio.urlshortener2.DataTransferObject;

public class UrlResponse {
    private String shortUrl;
    public UrlResponse(String shortUrl){
        this.shortUrl=shortUrl;
    }
    public void setShortUrl(String shortUrl){
        this.shortUrl=shortUrl;
    }
    public String getShortUrl(){
        return this.shortUrl;
    }
}
