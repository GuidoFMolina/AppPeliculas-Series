package com.demo.AppPeliculas.Series.dto;

import com.demo.AppPeliculas.Series.entities.Personaje;
import com.demo.AppPeliculas.Series.entities.Genero;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SerieDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "El título debe contener al menos 2 dígitos")
    private String titulo;
    @NotEmpty
    @Size(min = 4, max = 4)
    private Integer anioPublicacion;
    @NotEmpty
    @Size(max = 1, message = "La calificación debe contener un mínimo y máximo de 1 dígito.")
    private Integer calificacion;

    private Set<Personaje> personajes = new HashSet<>();

    private Set<Genero> generos = new HashSet<>();

    public SerieDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }

    public Set<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(Set<Genero> generos) {
        this.generos = generos;
    }
}
