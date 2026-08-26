package com.ignacio.urlshortener2.repository;

import  org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.HashMap;

@Repository
public class UrlRepository {
    private final Map<String, String> urls= new HashMap<>();

    public void guardar(String codigo, String url){
        urls.put(codigo, url);
    }

    public String buscar(String codigo){
        return urls.get(codigo);
    }

    public boolean existe(String codigo){
        return urls.containsKey(codigo);
    }

}
