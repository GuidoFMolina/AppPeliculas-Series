package com.demo.AppPeliculas.Series.dto;

import com.demo.AppPeliculas.Series.entities.Pelicula;
import com.demo.AppPeliculas.Series.entities.Serie;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class GeneroDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "El nombre del género debe contener no menos de 2 dígitos")
    private String nombre;

    private Set<Serie> series = new HashSet<>();

    private Set<Pelicula> peliculas = new HashSet<>();

    public GeneroDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Serie> getSeries() {
        return series;
    }

    public void setSeries(Set<Serie> series) {
        this.series = series;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
