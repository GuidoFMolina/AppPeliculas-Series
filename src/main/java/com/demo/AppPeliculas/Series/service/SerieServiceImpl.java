package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.SerieDTO;
import com.demo.AppPeliculas.Series.dto.SerieResponse;
import com.demo.AppPeliculas.Series.dto.SerieSlimDTO;
import com.demo.AppPeliculas.Series.entities.Genero;
import com.demo.AppPeliculas.Series.entities.Personaje;
import com.demo.AppPeliculas.Series.entities.Serie;
import com.demo.AppPeliculas.Series.exceptions.ResourceNotFoundException;
import com.demo.AppPeliculas.Series.repository.GeneroRepository;
import com.demo.AppPeliculas.Series.repository.PersonajeRepository;
import com.demo.AppPeliculas.Series.repository.SerieRepository;
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
public class SerieServiceImpl implements SerieService {
    @Autowired
    SerieRepository serieRepository;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    PersonajeRepository personajeRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public SerieDTO createSerie(SerieDTO serieDTO) {
        Serie serie = convertToSerie(serieDTO);
        Serie newSerie = serieRepository.save(serie);
        return convertToSerieDTO(newSerie);
    }

    @Override
    public SerieResponse getAllSeries(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Serie> pageSerie = serieRepository.findAll(pageable);
        List<Serie> serieList = pageSerie.getContent();
        List<SerieDTO> contents = serieList.stream().map(this::convertToSerieDTO).collect(Collectors.toList());
        SerieResponse serieResponse = new SerieResponse();
        serieResponse.setContents(contents);
        serieResponse.setPageNo(pageSerie.getNumber());
        serieResponse.setPageSize(pageSerie.getSize());
        serieResponse.setTotalElements(pageSerie.getTotalElements());
        serieResponse.setTotalPages(pageSerie.getTotalPages());
        serieResponse.setLastPage(pageSerie.isLast());

        return serieResponse;
    }

    @Override
    public List<SerieSlimDTO> getSerieByName(String name) {
        List<Serie> serieList = serieRepository.findAll();
        List<SerieDTO> serieDTOs = serieList.stream().
                map(this::convertToSerieDTO).toList();
        List<SerieDTO> serieDTOsFiltered = serieDTOs.stream().
                filter((s) -> s.getTitulo().equalsIgnoreCase(name)).toList();
        return serieDTOsFiltered.stream().map(this::convertToSerieSlimDTO).collect(Collectors.toList());
    }

    @Override
    public List<SerieSlimDTO> getSerieByYear(Integer year) {
        List<Serie> serieList = serieRepository.findAll();
        List<SerieDTO> serieDTOs = serieList.stream().map(this::convertToSerieDTO).toList();
        List<SerieDTO> serieDTOsFiltered = serieDTOs.stream().
                filter((s) -> s.getAnioPublicacion().equals(year)).toList();
        return serieDTOsFiltered.stream().map(this::convertToSerieSlimDTO).collect(Collectors.toList());
    }

    @Override
    public List<SerieSlimDTO> getSerieByGender(Long idGender) {
        Genero genero = generoRepository.findById(idGender).
                orElseThrow(() -> new ResourceNotFoundException("Genero", "id", idGender));
        Set<Serie> serieList = genero.getSeries();
        List<SerieDTO> serieDTOs = serieList.stream().map(this::convertToSerieDTO).toList();
        return serieDTOs.stream().map(this::convertToSerieSlimDTO).collect(Collectors.toList());
    }

    @Override
    public SerieDTO getSerieById(long id) {
        Serie serie = serieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serie", "id", id));
        return convertToSerieDTO(serie);
    }

    @Override
    public SerieDTO updateSerie(SerieDTO serieDTO, long idSerie) {
        Serie serie = serieRepository.findById(idSerie).
                orElseThrow(() -> new ResourceNotFoundException("Serie", "id", idSerie));
        serie.setTitulo(serieDTO.getTitulo());
        serie.setAnioPublicacion(serieDTO.getAnioPublicacion());
        serie.setCalificacion(serieDTO.getCalificacion());
        Serie newSerie = serieRepository.save(serie);
        return convertToSerieDTO(newSerie);
    }

    @Override
    public SerieDTO addCharacterToSerie(Long idSerie, Long idCharacter) {
        Serie serie = serieRepository.findById(idSerie).
                orElseThrow(() -> new ResourceNotFoundException("Serie", "id", idSerie));
        Personaje personaje = personajeRepository.findById(idCharacter).
                orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", idCharacter));
        serie.getPersonajes().add(personaje);
        Serie newSerie = serieRepository.save(serie);
        return convertToSerieDTO(newSerie);
    }
    @Override
    public void deleteSerie(Long id) {
     Serie serie = serieRepository.findById(id).
             orElseThrow(() -> new ResourceNotFoundException("Serie", "id", id));
     serieRepository.delete(serie);
    }
    @Override
    public SerieDTO deleteCharacterToSerie(Long idSerie, Long idCharacter) {
        Serie serie = serieRepository.findById(idSerie).
                orElseThrow(() -> new ResourceNotFoundException("Serie", "id", idSerie));
        Personaje personaje = personajeRepository.findById(idCharacter).
                orElseThrow(() -> new ResourceNotFoundException("Personaje", "id", idCharacter));
        serie.getPersonajes().remove(personaje);

        return convertToSerieDTO(serie);
    }
    public SerieDTO convertToSerieDTO(Serie serie){return modelMapper.map(serie, SerieDTO.class);}
    public Serie convertToSerie(SerieDTO serieDTO){
        return modelMapper.map(serieDTO, Serie.class);
    }
    public SerieSlimDTO convertToSerieSlimDTO(SerieDTO serieDTO){return modelMapper.map(serieDTO, SerieSlimDTO.class);}
}
