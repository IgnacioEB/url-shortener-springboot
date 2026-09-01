package com.ignacio.urlshortener2.controller;
import com.ignacio.urlshortener2.dto.UrlRequest;
import com.ignacio.urlshortener2.dto.UrlResponse;
import com.ignacio.urlshortener2.model.Url;
import com.ignacio.urlshortener2.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/acortar")
    public ResponseEntity<UrlResponse> acortar(@RequestBody UrlRequest request){

        String codigo= urlService.acortarUrl(request.getUrl());

        return ResponseEntity .status(HttpStatus.CREATED) .body(new UrlResponse(codigo, request.getUrl()));
    }

    @GetMapping("/{codigo:[a-zA-Z0-9]+}")
    public RedirectView redirigir(@PathVariable String codigo){
        Url url = urlService.obtenerUrl(codigo);
        return new RedirectView(url.getUrlOriginal());
    }


}

