package com.demo.AppPeliculas.Series.controller;

import com.demo.AppPeliculas.Series.dto.GeneroDTO;
import com.demo.AppPeliculas.Series.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gender")
public class GeneroController {
    @Autowired
    GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> getGenders() {
        return generoService.getAllsGender();
    }
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getGender(@PathVariable (name="id") Long id){
        return new ResponseEntity<>(generoService.getGenderById(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GeneroDTO> saveGender(@Valid @RequestBody GeneroDTO generoDTO){
        return new ResponseEntity<>(generoService.saveGender(generoDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> updateGender(@Valid @RequestBody GeneroDTO generoDTO, @PathVariable Long id){
        return new ResponseEntity<>(generoService.updateGender(generoDTO, id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGender(@PathVariable (name="id") Long id){
        generoService.deleteGender(id);
        return new ResponseEntity<>("El Genero se ha eliminado correctamente", HttpStatus.OK);
    }


}
