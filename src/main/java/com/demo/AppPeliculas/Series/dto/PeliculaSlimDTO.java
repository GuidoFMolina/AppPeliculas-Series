package com.demo.AppPeliculas.Series.dto;


import javax.validation.constraints.NotEmpty;

public class PeliculaSlimDTO {
    @NotEmpty
    private String titulo;
    @NotEmpty
    private Integer anioPublicacion;


    public PeliculaSlimDTO() {
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
