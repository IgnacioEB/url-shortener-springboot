package com.ignacio.urlshortener2.model;

import java.time.LocalDateTime;

public class Url {
    private String urlOriginal;
    private String codigo;
    private LocalDateTime fecha_creacion;

    public Url(String urlOriginal, String codigo, LocalDateTime fecha_creacion){
        this.urlOriginal=urlOriginal;
        this.codigo=codigo;
        this.fecha_creacion= fecha_creacion;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }
}
