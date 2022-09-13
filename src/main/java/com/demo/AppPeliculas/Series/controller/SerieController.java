package com.demo.AppPeliculas.Series.controller;


import com.demo.AppPeliculas.Series.dto.SerieDTO;
import com.demo.AppPeliculas.Series.dto.SerieResponse;
import com.demo.AppPeliculas.Series.dto.SerieSlimDTO;
import com.demo.AppPeliculas.Series.service.SerieService;
import com.demo.AppPeliculas.Series.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("series")
public class SerieController {
    @Autowired
    SerieService serieService;

    @GetMapping
    public SerieResponse getSeries(@RequestParam(value = "pageNo",
            defaultValue = AppConstants.DEFAULT_PAGE, required = false) Integer pageNo,
                                   @RequestParam(value = "pageSize",
                                           defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                   @RequestParam(value = "sortBy",
                                           defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
                                   @RequestParam(value = "sortOrder",
                                           defaultValue = AppConstants.SORT_DIRECTION_DEFAULT, required = false) String sortOrder) {
        return serieService.getAllSeries(pageNo, pageSize, sortBy, sortOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> getSerie(@PathVariable(name="id") long id){
        return new ResponseEntity<>(serieService.getSerieById(id), HttpStatus.OK);
    }
    @RequestMapping(params = "name")
    public List<SerieSlimDTO> getSerieByName(@RequestParam String name){
        return serieService.getSerieByName(name);
    }
    @RequestMapping(params = "year")
    public List<SerieSlimDTO> getSerieByYear(@RequestParam Integer year){
        return serieService.getSerieByYear(year);
    }
    @RequestMapping(params = "idGender")
    public List<SerieSlimDTO> getSerieByGender(@RequestParam long idGender){
        return serieService.getSerieByGender(idGender);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SerieDTO> saveSeries(@Valid @RequestBody SerieDTO serieDTO){
        return new ResponseEntity<>(serieService.createSerie(serieDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{idSerie}/characters/{idCharacter}")
    public ResponseEntity<SerieDTO> addCharacterToSerie(@Valid @PathVariable(name="idSerie") Long idSerie,
                                                            @PathVariable(name="idCharacter") Long idCharacter){
        return new ResponseEntity<>(serieService.addCharacterToSerie(idSerie,idCharacter), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SerieDTO> updateSeries(@Valid @RequestBody SerieDTO serie, @PathVariable(name="id") Long idSerie){
        return new ResponseEntity<>(serieService.updateSerie(serie, idSerie), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSerie(@PathVariable(name="id") Long id){
        serieService.deleteSerie(id);
        return new ResponseEntity<>("Serie eliminada correctamente", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idSerie}/characters/{idCharacter}")
    public ResponseEntity<SerieDTO> deletecharactersToSerie(@PathVariable(name="idSerie") Long idSerie,
                                @PathVariable(name="idCharacter") Long idCharacter){
        return new ResponseEntity<>(serieService.deleteCharacterToSerie(idSerie, idCharacter), HttpStatus.OK);
    }
}
