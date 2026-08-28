package com.ignacio.urlshortener2.model;

import java.time.LocalDateTime;

public class Url {
    private String urlOriginal;
    private String codigo;
    private LocalDateTime fechaCreacion;

    public Url(String urlOriginal, String codigo, LocalDateTime fechaCreacion){
        this.urlOriginal=urlOriginal;
        this.codigo=codigo;
        this.fechaCreacion = fechaCreacion;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
