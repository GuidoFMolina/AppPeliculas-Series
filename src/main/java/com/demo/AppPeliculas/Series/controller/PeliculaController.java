package com.demo.AppPeliculas.Series.controller;

import com.demo.AppPeliculas.Series.dto.*;
import com.demo.AppPeliculas.Series.entities.Genero;
import com.demo.AppPeliculas.Series.entities.Pelicula;
import com.demo.AppPeliculas.Series.entities.Personaje;
import com.demo.AppPeliculas.Series.service.GeneroService;
import com.demo.AppPeliculas.Series.service.PeliculaService;
import com.demo.AppPeliculas.Series.service.PersonajeService;
import com.demo.AppPeliculas.Series.utilities.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("movies")
public class PeliculaController {
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public PeliculaResponse getFilms(@RequestParam(value = "pageNo",
            defaultValue = AppConstants.DEFAULT_PAGE, required = false) Integer pageNo,
                                     @RequestParam(value = "pageSize",
                                          defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                     @RequestParam(value = "sortBy",
                                          defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
                                     @RequestParam(value = "order",
                                          defaultValue = AppConstants.SORT_DIRECTION_DEFAULT, required = false) String sortOrder) {
        return peliculaService.getAllFilm(pageNo, pageSize, sortBy, sortOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getFilmById(@PathVariable long id) {
        return new ResponseEntity<>(peliculaService.getFilmById(id), HttpStatus.OK);
    }
    @RequestMapping(params = {"name"})
    public List<PeliculaSlimDTO> getFilmByName(@RequestParam("name") String name) {
        return peliculaService.getFilmByName(name);
    }
    @RequestMapping(params = {"idGender"})
    public List<PeliculaSlimDTO> getFilmByGenero(@RequestParam("idGender") Long idGenero) {
        return peliculaService.getFilmByGenero(idGenero);
    }
    @RequestMapping(params = {"year"})
    public List<PeliculaSlimDTO> getFilmByAnio(@RequestParam("year") Integer year){
        return peliculaService.getFilmByAnio(year);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PeliculaDTO> saveFilm(@Valid @RequestBody PeliculaDTO peliculaDTO) {
        return new ResponseEntity<>(peliculaService.createFilm(peliculaDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> addCharacters(@Valid @PathVariable Long idMovie,
                                                     @PathVariable Long idCharacter) {
        return new ResponseEntity<PeliculaDTO>(peliculaService.addCharacter(idMovie,idCharacter), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updateFilm(@Valid @PathVariable long id, @RequestBody PeliculaDTO peliculaDTO) {
    return new ResponseEntity<>(peliculaService.updateFilm(peliculaDTO, id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable(name="id") Long id){
        peliculaService.removeFilm(id);
        return new ResponseEntity<>("Publicacion eliminada correctamente", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> deleteCharacter(@PathVariable Long idMovie,
                                                           @PathVariable Long idCharacter){
        return new ResponseEntity<>(peliculaService.removeCharacter(idMovie, idCharacter), HttpStatus.OK);
    }

}
