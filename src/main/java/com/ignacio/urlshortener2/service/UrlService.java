package com.ignacio.urlshortener2.service;

import com.ignacio.urlshortener2.exception.UrlNotFoundException;
import com.ignacio.urlshortener2.model.Url;
import com.ignacio.urlshortener2.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.UUID;
import java.net.URI;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository= urlRepository;
    }


    public String acortarUrl(String url){
        if(url==null || url.isBlank()){
            throw new IllegalArgumentException("URL cannot be empty");
        }

        try{
            URI uri= new URI(url);
            if (!"http".equals(uri.getScheme()) && !"https".equals(uri.getScheme())) {
                throw new IllegalArgumentException("URL must use http or https");
            }

        }catch (URISyntaxException e){
            throw new IllegalArgumentException("The URL is not valid");
        }
        String codigo= generarCodigo();
        urlRepository.guardar(codigo, url);
        return codigo;
    }

    private String generarCodigo(){
        String codigo;
        do{
             codigo= UUID.randomUUID().toString().substring(0,6);
        }while(urlRepository.existe(codigo));
        return codigo;
    }

    public Url obtenerUrl(String codigo){
        Url url= urlRepository.buscar(codigo);
        if(url==null){
            throw new UrlNotFoundException("URL doesn't exist");
        }
        return url;
    }



}
