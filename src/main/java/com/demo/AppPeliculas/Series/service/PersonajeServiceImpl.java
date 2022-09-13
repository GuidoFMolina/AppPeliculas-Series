package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.*;
import com.demo.AppPeliculas.Series.entities.Pelicula;
import com.demo.AppPeliculas.Series.entities.Personaje;
import com.demo.AppPeliculas.Series.exceptions.ResourceNotFoundException;
import com.demo.AppPeliculas.Series.repository.PersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PersonajeRepository personajeRepository;

    @Override
    public PersonajeDTO createCharacter(PersonajeDTO personajeDTO) {
        //Convertimos DTO -> Entity
        Personaje actor = convertToCharacter(personajeDTO);
        Personaje newActor = personajeRepository.save(actor);
        //Convertimos Entity a DTO response
        return convertToCharacterDTO(newActor);
    }

    @Override
    public PersonajeResponse getAllCharacter(int pageNo, int pageSize, String sortBy, String sortOrder) {

        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Personaje> actors = personajeRepository.findAll(pageable);
        List<Personaje> actorsList = actors.getContent();
        List<PersonajeDTO> contents = actorsList.stream().
                map(this::convertToCharacterDTO).collect(Collectors.toList());

        PersonajeResponse personajeResponse = new PersonajeResponse();
        personajeResponse.setContents(contents);
        personajeResponse.setNumberPage(actors.getNumber());
        personajeResponse.setSizePage(actors.getSize());
        personajeResponse.setTotalItems(actors.getTotalElements());
        personajeResponse.setTotalPages(actors.getTotalPages());
        personajeResponse.setLastPage(actors.isLast());
        return personajeResponse;
    }

    @Override
    public List<PersonajeDTO> findAll() {
        List<Personaje> lista = personajeRepository.findAll();
        return lista.stream().map(this::convertToCharacterDTO).collect(Collectors.toList());
    }

    @Override
    public PersonajeDTO getCharacterById(Long actorId) {
        Personaje actor = personajeRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", actorId));
        return convertToCharacterDTO(actor);
    }

    @Override
    public PersonajeDTO updateCharacter(PersonajeDTO personajeDTO, long actorId) {
        Personaje actor = personajeRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", actorId));
        actor.setNombre(personajeDTO.getNombre());
        actor.setEdad(personajeDTO.getEdad());
        actor.setHistoria(personajeDTO.getHistoria());
        actor.setPeso(personajeDTO.getPeso());

        Personaje newActor = personajeRepository.save(actor);

        return convertToCharacterDTO(newActor);
    }

    @Override
    public void removeCharacter(Long actorId) {
        Personaje actor = personajeRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", actorId));
        personajeRepository.delete(actor);
    }

    @Override
    public List<PersonajeSlimDTO> getCharacterByAge(Integer edad) {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeDTO> listaDTO = personajes.stream().map(this::convertToCharacterDTO).toList();
        List<PersonajeDTO> filteredList = listaDTO.stream().filter((p) -> p.getEdad().equals(edad)).toList();
        return filteredList.stream().map(this::convertToCharacterSlim).collect(Collectors.toList());
    }

    @Override
    public List<PersonajeSlimDTO> getCharacterByName(String nombre) {
        List<Personaje> personajes = personajeRepository.findAll();
        List<PersonajeDTO> listaDTO = personajes.stream().map(this::convertToCharacterDTO).toList();
        List<PersonajeDTO> filteredList = listaDTO.stream().
                filter((p) -> p.getNombre().equalsIgnoreCase(nombre)).toList();
        return filteredList.stream().map(this::convertToCharacterSlim).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSlimDTO> getMovieByCharacter(Long id) {
       Personaje personaje = personajeRepository.findById(id).
               orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", id));
       Set<Pelicula> peliculas = personaje.getPeliculas();
       List<PeliculaDTO> peliculasDTO = peliculas.stream().map(this::convertToFilmDTO).toList();
        return peliculasDTO.stream().map(this::convertToFilmSlimDTO).collect(Collectors.toList()); //
    }
    public PersonajeDTO convertToCharacterDTO(Personaje actor) {
        return modelMapper.map(actor, PersonajeDTO.class);
    }
    public Personaje convertToCharacter(PersonajeDTO personajeDTO){
        return modelMapper.map(personajeDTO, Personaje.class);
    }
    public PersonajeSlimDTO convertToCharacterSlim(PersonajeDTO personajeDTO){
        return modelMapper.map(personajeDTO, PersonajeSlimDTO.class);
    }
    public PeliculaDTO convertToFilmDTO(Pelicula pelicula){
        return modelMapper.map(pelicula, PeliculaDTO.class);
    }
    public PeliculaSlimDTO convertToFilmSlimDTO(PeliculaDTO filmDTO){
        return modelMapper.map(filmDTO, PeliculaSlimDTO.class);
    }
}
