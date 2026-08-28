package com.ignacio.urlshortener2.dto;

public class UrlResponse {
    private String shortUrl;
    private String originalUrl;
    public UrlResponse(String shortUrl, String originalUrl){
        this.shortUrl=shortUrl;
        this.originalUrl=originalUrl;
    }
    public void setShortUrl(String shortUrl){
        this.shortUrl=shortUrl;
    }
    public String getShortUrl(){
        return this.shortUrl;
    }
    public String getOriginalUrl(){
        return this.originalUrl;
    }
}
