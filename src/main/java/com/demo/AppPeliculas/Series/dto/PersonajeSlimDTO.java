package com.demo.AppPeliculas.Series.dto;

import javax.validation.constraints.NotEmpty;

public class PersonajeSlimDTO {
    @NotEmpty
    private String nombre;

    public PersonajeSlimDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
