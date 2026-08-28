package com.ignacio.urlshortener2.controller;

import com.ignacio.urlshortener2.exception.UrlExceptionHandler;
import com.ignacio.urlshortener2.exception.UrlNotFoundException;
import com.ignacio.urlshortener2.model.Url;
import com.ignacio.urlshortener2.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class UrlControllerTest {
    @Test
    void deberiaAcortarUrl() throws Exception{
        UrlService urlService = mock(UrlService.class);
        when(urlService.acortarUrl("http://www.google.com")).thenReturn("abc123");
        UrlController urlController= new UrlController(urlService);
        MockMvc mockMvc= MockMvcBuilders
                .standaloneSetup(urlController)
                .build();

        mockMvc.perform(post("/acortar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"http://www.google.com\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.shortUrl").value("abc123"))
                .andExpect((jsonPath("$.originalUrl").value("http://www.google.com")));

    }


    @Test
    void deberiaTirarBadRequest() throws Exception{
        UrlService urlService = mock(UrlService.class);
        when(urlService.acortarUrl("google.com")).thenThrow(new IllegalArgumentException("La URL debe usar http o https"));
        UrlController urlController= new UrlController(urlService);
        MockMvc mockMvc= MockMvcBuilders
                .standaloneSetup(urlController)
                .setControllerAdvice(new UrlExceptionHandler())
                .build();

        mockMvc.perform(post("/acortar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"google.com\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("La URL debe usar http o https"))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    void deberiaTirarNotFound() throws Exception{
        UrlService urlService = mock(UrlService.class);
        when(urlService.obtenerUrl("abc123")).thenThrow(new UrlNotFoundException("La URL no existe"));
        UrlController urlController= new UrlController(urlService);
        MockMvc mockMvc= MockMvcBuilders
                .standaloneSetup(urlController)
                .setControllerAdvice(new UrlExceptionHandler())
                .build();

        mockMvc.perform(get("/abc123"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("La URL no existe"))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));


    }


    @Test
    void deberiaRedireccionar() throws  Exception{
        UrlService urlService = mock(UrlService.class);
        when(urlService.obtenerUrl("abc123")).thenReturn(new Url("http://www.google.com", "abc123", LocalDateTime.now()));
        UrlController urlController= new UrlController(urlService);
        MockMvc mockMvc= MockMvcBuilders
                .standaloneSetup(urlController)
                .build();

        mockMvc.perform(get("/abc123"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://www.google.com"));

    }
}
