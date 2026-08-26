package com.ignacio.urlshortener2.controller;
import com.ignacio.urlshortener2.Service.UrlService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/hola")
    public String hola() {
        return urlService.saludar();
    }
    @PostMapping("/acortar")
    public String acortar(String url){
        return urlService.acortarUrl(url);
    }
    @GetMapping("/{codigo}")
    public RedirectView redirigir(@PathVariable String codigo){
        String url = urlService.obtenerUrl(codigo);
        return new RedirectView(url);
    }


}
