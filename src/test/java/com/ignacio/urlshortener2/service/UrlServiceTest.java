package com.ignacio.urlshortener2.service;

import com.ignacio.urlshortener2.exception.UrlNotFoundException;
import com.ignacio.urlshortener2.model.Url;
import com.ignacio.urlshortener2.repository.UrlRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UrlServiceTest {
    @Test
    void deberiaAcortarUnaUrlValida() {

        UrlRepository repository = mock(UrlRepository.class);

        UrlService service = new UrlService(repository);

        String codigo = service.acortarUrl("https://www.google.com");

        assertNotNull(codigo);
        verify(repository).guardar(codigo, "https://www.google.com");
    }

    @Test
    void deberiaRechazarUrlSinHttp() {

        UrlRepository repository = mock(UrlRepository.class);
        UrlService service = new UrlService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.acortarUrl("www.google.com")
        );
    }
    @Test
    void deberiaRechazarUrlVacia() {

        UrlRepository repository = mock(UrlRepository.class);
        UrlService service = new UrlService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.acortarUrl("")
        );
    }
    @Test
    void deberiaLanzarExcepcionSiLaUrlNoExiste() {

        UrlRepository repository = mock(UrlRepository.class);
        UrlService service = new UrlService(repository);

        assertThrows(
                UrlNotFoundException.class,
                () -> service.obtenerUrl("abcdef")
        );
    }
    @Test
    void deberiaRechazarUrlConSintaxisInvalida() {

        UrlRepository repository = mock(UrlRepository.class);
        UrlService service = new UrlService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.acortarUrl("http://esto es una url invalida")
        );
    }

    @Test
    void deberiaObtenerUrlExistente() {
        UrlRepository repository = mock(UrlRepository.class);
        UrlService service = new UrlService(repository);

        when(repository.buscar("abc123"))
                .thenReturn(new Url("http://www.google.com", "abc123", LocalDateTime.now()));

        Url resultado = service.obtenerUrl("abc123");

        assertEquals("http://www.google.com", resultado.getUrlOriginal());
        assertEquals("abc123", resultado.getCodigo());
    }
}
