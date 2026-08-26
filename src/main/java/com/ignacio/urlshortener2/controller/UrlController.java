package com.ignacio.urlshortener2.controller;
import com.ignacio.urlshortener2.DataTransferObject.UrlRequest;
import com.ignacio.urlshortener2.DataTransferObject.UrlResponse;
import com.ignacio.urlshortener2.Service.UrlService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public UrlResponse acortar(@RequestBody UrlRequest request){

        String codigo= urlService.acortarUrl(request.getUrl());
        return new UrlResponse(codigo);
    }

    @GetMapping("/{codigo}")
    public RedirectView redirigir(@PathVariable String codigo){
        String url = urlService.obtenerUrl(codigo);
        return new RedirectView(url);
    }


}
