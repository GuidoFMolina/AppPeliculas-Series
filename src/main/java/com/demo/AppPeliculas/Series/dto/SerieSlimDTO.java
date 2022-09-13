package com.demo.AppPeliculas.Series.dto;

import javax.validation.constraints.NotEmpty;

public class SerieSlimDTO {
    @NotEmpty
    private String titulo;
    @NotEmpty
    private Integer anioPublicacion;

    public SerieSlimDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
