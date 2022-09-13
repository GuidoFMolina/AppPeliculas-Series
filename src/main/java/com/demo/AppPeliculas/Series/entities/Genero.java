package com.demo.AppPeliculas.Series.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table (name = "Generos", uniqueConstraints = {@UniqueConstraint(columnNames = "Nombre")})
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;
    @JsonIgnore
   @ManyToMany(cascade = CascadeType.ALL, mappedBy = "generos")
    private Set<Serie> series = new HashSet<>();
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "generos")
    private Set<Pelicula> peliculas= new HashSet<>();

    public Genero() {
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

    public void setNombre(String name) {
        this.nombre = name;
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
