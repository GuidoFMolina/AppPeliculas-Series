package com.demo.AppPeliculas.Series.controller;

import com.demo.AppPeliculas.Series.dto.PeliculaSlimDTO;
import com.demo.AppPeliculas.Series.dto.PersonajeDTO;
import com.demo.AppPeliculas.Series.dto.PersonajeResponse;
import com.demo.AppPeliculas.Series.dto.PersonajeSlimDTO;
import com.demo.AppPeliculas.Series.service.PersonajeService;
import com.demo.AppPeliculas.Series.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/characters")
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    //Metodo listar Personajes segun pagina, tamaño de pagina y ordenar de forma
    //Ascendente o Descendente
   @GetMapping
   public PersonajeResponse getActors(@RequestParam(value = "pageNo",
           defaultValue = AppConstants.DEFAULT_PAGE, required = false) int pageNo,
                                      @RequestParam(value = "pageSize",
                                          defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                      @RequestParam(value = "sortBy",
                                          defaultValue = AppConstants.SORT_BY_DEFAULT, required = false) String sortBy,
                                      @RequestParam(value = "sortOrder",
                                          defaultValue = AppConstants.SORT_DIRECTION_DEFAULT, required = false) String sortOrder) {
       return personajeService.getAllCharacter(pageNo, pageSize, sortBy, sortOrder);
   }
    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getCharacterById(@PathVariable(name="id")Long id){
        return  ResponseEntity.ok(personajeService.getCharacterById(id));
    }

   @RequestMapping(params = "age")
   public List<PersonajeSlimDTO> getCharacterByAge(@RequestParam(value = "age") Integer edad){
       return personajeService.getCharacterByAge(edad);
   }
   @RequestMapping(params = "name")
   public List<PersonajeSlimDTO> getCharacterByName(@RequestParam(value = "name") String nombre){
       return personajeService.getCharacterByName(nombre);
   }
   @RequestMapping(params = "idMovie")
   public List<PeliculaSlimDTO> getCharacterByMovie(@RequestParam(value = "idMovie") Long id){
       return personajeService.getMovieByCharacter(id);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PersonajeDTO> saveCharacter(@Valid @RequestBody PersonajeDTO personajeDTO) {
        return new ResponseEntity<>(personajeService.createCharacter(personajeDTO), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updateCharacter(@Valid@RequestBody PersonajeDTO personajeDTO,
                                                        @PathVariable(name="id") Long id){
       return new ResponseEntity<>(personajeService.updateCharacter(personajeDTO, id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable(name="id") Long id){
        personajeService.removeCharacter(id);
       return new ResponseEntity<>("Publicacion eliminada correctamente", HttpStatus.OK);
    }


}
