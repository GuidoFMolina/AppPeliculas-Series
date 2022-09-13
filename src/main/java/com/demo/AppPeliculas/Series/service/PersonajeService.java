package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.PeliculaSlimDTO;
import com.demo.AppPeliculas.Series.dto.PersonajeDTO;
import com.demo.AppPeliculas.Series.dto.PersonajeResponse;
import com.demo.AppPeliculas.Series.dto.PersonajeSlimDTO;

import java.util.List;


public interface PersonajeService {
    public PersonajeDTO createCharacter(PersonajeDTO personajeDTO);
    public PersonajeResponse getAllCharacter(int pageNo, int pageSize, String sortBy, String sortOrder);
    public List<PersonajeDTO> findAll();
    public PersonajeDTO getCharacterById(Long actorId);
    public PersonajeDTO updateCharacter(PersonajeDTO personajeDTO, long actorId);
    public void removeCharacter(Long actorId);
    public List<PersonajeSlimDTO> getCharacterByAge(Integer edad);
    public List<PersonajeSlimDTO> getCharacterByName(String nombre);
    public List<PeliculaSlimDTO> getMovieByCharacter(Long id);


}
