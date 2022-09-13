package com.demo.AppPeliculas.Series.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity(name = "Series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Titulo", nullable = false, length = 225)
    private String titulo;
    @Column(name = "Año", length = 225, nullable = false)
    private Integer anioPublicacion;
    @Column(name = "Calificacion", length = 225, nullable = false)
    private Integer calificacion;
   @ManyToMany(cascade = {CascadeType.ALL})
   @JsonIgnore
   @JoinTable(name="SeriePersonaje", joinColumns={@JoinColumn(name="IdPersonaje")},
           inverseJoinColumns={@JoinColumn(name="IdSerie")})
    private Set<Personaje> personajes = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinTable(name="SerieGeneros", joinColumns={@JoinColumn(name="IdPersonaje")},
            inverseJoinColumns={@JoinColumn(name="IdGeneros")})
    private Set<Genero> generos = new HashSet<>();

    public Serie() {
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
