package com.ignacio.urlshortener2.dto;

public class UrlRequest {
    private String url;
    private UrlRequest(){

    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url=url;
    }


}
