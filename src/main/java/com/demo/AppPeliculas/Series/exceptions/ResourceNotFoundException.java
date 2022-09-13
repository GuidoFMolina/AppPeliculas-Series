package com.demo.AppPeliculas.Series.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String nombreRecurso;
    private String nombreCampo;
    private Long idCampo;


    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDelCampo) {
        super(String.format("%s no encontrada con : %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
        this.nombreRecurso = nombreDelRecurso;
        this.nombreCampo = nombreDelCampo;
        this.idCampo = valorDelCampo;
    }


    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public Long getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }
}
