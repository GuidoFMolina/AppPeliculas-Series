package com.demo.AppPeliculas.Series.service;


import com.demo.AppPeliculas.Series.dto.PeliculaDTO;
import com.demo.AppPeliculas.Series.dto.PeliculaResponse;
import com.demo.AppPeliculas.Series.dto.PeliculaSlimDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface PeliculaService {
    public PeliculaDTO createFilm(PeliculaDTO peliculaDTO);
    public List<PeliculaDTO> findAll();
    public PeliculaResponse getAllFilm(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);
    public PeliculaDTO getFilmById(Long filmId);
    public List<PeliculaSlimDTO> getFilmByName(String name);
    public List<PeliculaSlimDTO> getFilmByGenero(Long idGenero);
    public List<PeliculaSlimDTO> getFilmByAnio(Integer idAnio);
    public PeliculaDTO updateFilm(PeliculaDTO peliculaDTO, long filmId);
    public PeliculaDTO addCharacter(Long idMovie, Long idCharacter);
    public void removeFilm(Long filmId);
    public PeliculaDTO removeCharacter(Long idMovie, Long idCharacter);

}
