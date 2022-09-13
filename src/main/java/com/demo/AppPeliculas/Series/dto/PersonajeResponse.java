package com.demo.AppPeliculas.Series.dto;

import java.util.List;

public class PersonajeResponse {
    private List<PersonajeDTO> contents;
    private Integer numberPage;
    private Integer sizePage;
    private Long totalItems;
    private Integer totalPages;
    private boolean lastPage;

    public PersonajeResponse() {
    }

    public List<PersonajeDTO> getContents() {
        return contents;
    }

    public void setContents(List<PersonajeDTO> contents) {
        this.contents = contents;
    }

    public Integer getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage) {
        this.numberPage = numberPage;
    }

    public Integer getSizePage() {
        return sizePage;
    }

    public void setSizePage(Integer sizePage) {
        this.sizePage = sizePage;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
