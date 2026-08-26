package com.ignacio.urlshortener2.Service;

import com.ignacio.urlshortener2.model.Url;
import com.ignacio.urlshortener2.repository.UrlRepository;
import org.springframework.stereotype.Service;
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
            if(!uri.getScheme().equals("http") && !uri.getScheme().equals("https")){
                throw new IllegalArgumentException("La URL debería usar http o https");
            }

        }catch (Exception e){
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
        String url=urlRepository.buscar(codigo);
        if(url==null){
            throw new IllegalArgumentException("La URL no existe");

        }
        return url;
    }



}
