package com.demo.AppPeliculas.Series.dto;
import com.demo.AppPeliculas.Series.entities.Pelicula;
import com.demo.AppPeliculas.Series.entities.Serie;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class PersonajeDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "El nombre del personaje debe contener un minimo de 2 digitos")
    private String nombre;
    @NotEmpty
    @Size(min = 10, message = "La historia del personaje debe contener un minimo de 10 digitos")
    private String historia;
    @NotEmpty
    private Integer edad;
    @NotEmpty
    private Double peso;

   private Set<Pelicula> peliculas = new HashSet<>();

   private Set<Serie> series = new HashSet<>();

    public PersonajeDTO() {
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

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Set<Serie> getSeries() {
        return series;
    }

    public void setSeries(Set<Serie> series) {
        this.series = series;
    }
}
