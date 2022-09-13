package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.PeliculaDTO;
import com.demo.AppPeliculas.Series.dto.PeliculaResponse;
import com.demo.AppPeliculas.Series.dto.PeliculaSlimDTO;
import com.demo.AppPeliculas.Series.entities.Genero;
import com.demo.AppPeliculas.Series.entities.Pelicula;
import com.demo.AppPeliculas.Series.entities.Personaje;
import com.demo.AppPeliculas.Series.exceptions.ResourceNotFoundException;
import com.demo.AppPeliculas.Series.repository.GeneroRepository;
import com.demo.AppPeliculas.Series.repository.PeliculaRepository;
import com.demo.AppPeliculas.Series.repository.PersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PeliculaDTO createFilm(PeliculaDTO peliculaDTO){

        Pelicula film = convertToFilm(peliculaDTO);
        Pelicula newFilm = peliculaRepository.save(film);
        return convertToFilmDTO(newFilm);
    }

    @Override
    public List<PeliculaDTO> findAll() {
        List<Pelicula> list = peliculaRepository.findAll();
        return list.stream().map(this::convertToFilmDTO).collect(Collectors.toList());
    }

    @Override
    public PeliculaResponse getAllFilm(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Pelicula> films = peliculaRepository.findAll(pageable);

        List<Pelicula> filmList = films.getContent();
        List<PeliculaDTO>contents = filmList.stream().map(this::convertToFilmDTO).collect(Collectors.toList());
        PeliculaResponse peliculaResponse = new PeliculaResponse();
        peliculaResponse.setContents(contents);
        peliculaResponse.setNumberPage(films.getNumber());
        peliculaResponse.setSizePage(films.getSize());
        peliculaResponse.setTotalItems(films.getTotalElements());
        peliculaResponse.setTotalPages(films.getTotalPages());
        peliculaResponse.setLastPage(films.isLast());
        return peliculaResponse;
    }

    @Override
    public PeliculaDTO getFilmById(Long filmId) {
        Pelicula film = peliculaRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", filmId));
        return convertToFilmDTO(film);
    }

    @Override
    public List<PeliculaSlimDTO> getFilmByName(String name) {
        List<Pelicula> films = peliculaRepository.findAll();
        List<PeliculaDTO> filmDTO = films.stream().map(this::convertToFilmDTO).toList();
        List<PeliculaDTO> filmDTOFiltradas = filmDTO.stream().
                filter((p) -> p.getTitulo().equalsIgnoreCase(name)).toList();
        return filmDTOFiltradas.stream().map(this::convertToFilmSlimDTO).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSlimDTO> getFilmByGenero(Long idGenero) {
        Genero genero = generoRepository.findById(idGenero).
                orElseThrow(() -> new ResourceNotFoundException("Genero", "ID", idGenero));
        Set<Pelicula> filmsByGenero = genero.getPeliculas();
        List<PeliculaDTO> filmDTOs = filmsByGenero.stream().map(this::convertToFilmDTO).toList();

        return filmDTOs.stream().map(this::convertToFilmSlimDTO).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSlimDTO> getFilmByAnio(Integer anio) {
        List<Pelicula> films = peliculaRepository.findAll();
        List<PeliculaDTO> filmDTOs = films.stream().map(this::convertToFilmDTO).toList();
        List<PeliculaDTO> filmsFiltrada = filmDTOs.stream().filter((p) ->
                p.getAnioPublicacion().equals(anio)).toList();
        return filmsFiltrada.stream().map(this::convertToFilmSlimDTO).collect(Collectors.toList());
    }

    @Override
    public PeliculaDTO updateFilm(PeliculaDTO peliculaDTO, long filmId) {
        Pelicula film = peliculaRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", filmId));
        film.setTitulo(peliculaDTO.getTitulo());
        film.setCalificacion(peliculaDTO.getCalificacion());
        film.setAnioPublicacion(peliculaDTO.getAnioPublicacion());
        Pelicula newFilm = peliculaRepository.save(film);
        return convertToFilmDTO(newFilm);
    }

    @Override
    public PeliculaDTO addCharacter(Long idMovie, Long idCharacter) {
        Personaje personaje = personajeRepository.findById(idCharacter).
                orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", idCharacter));
        Pelicula film = peliculaRepository.findById(idMovie).
                orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", idMovie));
        film.getPersonajes().add(personaje);
        Pelicula newFilm = peliculaRepository.save(film);
        return convertToFilmDTO(newFilm);
    }

    @Override
    public void removeFilm(Long filmId) {
        Pelicula film = peliculaRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", filmId));
        peliculaRepository.deleteById(filmId);
    }

    @Override
    public PeliculaDTO removeCharacter(Long idMovie, Long idCharacter) {
        Pelicula film = peliculaRepository.findById(idMovie).
                orElseThrow(() -> new ResourceNotFoundException("Pelicula", "id", idMovie));
        Personaje personaje = personajeRepository.findById(idCharacter).
                orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", idCharacter));
        film.getPersonajes().remove(personaje);
        return convertToFilmDTO(film);
    }


    //Convertimos Entity a DTO
    public PeliculaDTO convertToFilmDTO(Pelicula film){
        return modelMapper.map(film, PeliculaDTO.class);
    }
    //Convertimos DTO to Entity
    public Pelicula convertToFilm(PeliculaDTO peliculaDTO){
        return modelMapper.map(peliculaDTO, Pelicula.class);
    }
    public PeliculaSlimDTO convertToFilmSlimDTO(PeliculaDTO peliculaDTO){
        return modelMapper.map(peliculaDTO, PeliculaSlimDTO.class);
    }
}
