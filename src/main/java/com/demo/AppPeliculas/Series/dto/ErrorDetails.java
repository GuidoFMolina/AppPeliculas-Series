package com.demo.AppPeliculas.Series.dto;

import java.util.Date;

public class ErrorDetails {
    private Date marcaDate;
    private String errorMessage;
    private String description;

    public ErrorDetails() {
    }

    public ErrorDetails(Date marcaDate, String errorMessage, String description) {
        this.marcaDate = marcaDate;
        this.errorMessage = errorMessage;
        this.description = description;
    }

    public Date getMarcaDate() {
        return marcaDate;
    }

    public void setMarcaDate(Date marcaDate) {
        this.marcaDate = marcaDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
