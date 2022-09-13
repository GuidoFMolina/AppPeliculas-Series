package com.demo.AppPeliculas.Series.entities;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Personajes", uniqueConstraints = {@UniqueConstraint(columnNames = "Nombre")})
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre", length = 225, nullable = false)
    private String nombre;
    @Column(name = "Historia", length = 225, nullable = false)
    private String historia;
    @Column(name = "Edad", length = 225, nullable = false)
    private Integer edad;
    @Column(name = "Peso", length = 225, nullable = false)
    private Double peso;
    @ManyToMany(mappedBy = "personajes")
    @JsonIgnore
    private Set<Pelicula> peliculas = new HashSet<>();
   @ManyToMany(mappedBy = "personajes")
   @JsonIgnore
    private Set<Serie> series = new HashSet<>();

    public Personaje() {
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
