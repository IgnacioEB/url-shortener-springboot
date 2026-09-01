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
    public ResponseEntity<UrlResponse> acortar(@RequestBody UrlRequest request){//con el @RequestBody tomo el body de la petición HTTP y lo convierto en un objeto UrlRequest
        //Uso ResponseEntity para tener control completo de la respuesta HTTP que da nuestro endpoint
        String codigo= urlService.acortarUrl(request.getUrl());

        return ResponseEntity .status(HttpStatus.CREATED) .body(new UrlResponse(codigo, request.getUrl()));
        //decido qué status y qué body devuelve
    }
    //RedirectView hace que el servidor le diga al navegador que se vaya a otra URL
    @GetMapping("/{codigo:[a-zA-Z0-9]+}")//codigo alfanumerico que identifica a la URL acortada
    public RedirectView redirigir(@PathVariable String codigo){//Con PathVariable obtengo el valor que viene dentro de la url, en este caso, el codigo
        Url url = urlService.obtenerUrl(codigo);
        return new RedirectView(url.getUrlOriginal());
        //redirecciona a la URL original
    }


}

