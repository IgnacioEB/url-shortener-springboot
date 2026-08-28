package com.ignacio.urlshortener2.service;

import com.ignacio.urlshortener2.exception.UrlNotFoundException;
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


    public String saludar(){
        return "Hola desde Spring boot";
    }

    public String acortarUrl(String url){
        if(url==null || url.isBlank()){
            throw new IllegalArgumentException("La URL no puede estar vacia");
        }

        try{
            URI uri= new URI(url);
            if (!"http".equals(uri.getScheme()) && !"https".equals(uri.getScheme())) {
                throw new IllegalArgumentException("La URL debe usar http o https");
            }

        }catch (URISyntaxException e){
            throw new IllegalArgumentException("La URL no es valida");
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

    public String obtenerUrl(String codigo){
        String url= urlRepository.buscar(codigo);
        if(url==null){
            throw new UrlNotFoundException("La URL no existe");
        }
        return url;
    }



}
