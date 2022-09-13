package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.GeneroDTO;
import com.demo.AppPeliculas.Series.entities.Genero;
import com.demo.AppPeliculas.Series.exceptions.ResourceNotFoundException;
import com.demo.AppPeliculas.Series.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    @Override
    public GeneroDTO saveGender(GeneroDTO generoDTO) {
        Genero genero = convertToGender(generoDTO);
        Genero newGenero = generoRepository.save(genero);
        return convertToGenderDTO(newGenero);
    }

    @Override
    public List<GeneroDTO> getAllsGender() {
        List<Genero> generos = generoRepository.findAll();
        return generos.stream().map(this::convertToGenderDTO).collect(Collectors.toList());
    }

    @Override
    public GeneroDTO getGenderById(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Genero", "id", id));
        return convertToGenderDTO(genero);
    }

    @Override
    public GeneroDTO updateGender(GeneroDTO generoDTO, Long id) {
        Genero genero = generoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));
        genero.setNombre(generoDTO.getNombre());
        genero.setPeliculas(generoDTO.getPeliculas());
        genero.setSeries(generoDTO.getSeries());
        Genero newGender = generoRepository.save(genero);
        return convertToGenderDTO(newGender);
    }

    @Override
    public void deleteGender(Long id) {
    Genero gender = generoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", id));
    generoRepository.delete(gender);
    }

    public GeneroDTO convertToGenderDTO(Genero gender){
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(gender.getId());
        generoDTO.setNombre(gender.getNombre());
        generoDTO.setPeliculas(gender.getPeliculas());
        generoDTO.setSeries(gender.getSeries());
        return generoDTO;
    }
    public Genero convertToGender(GeneroDTO generoDTO){
        Genero genero = new Genero();
        genero.setNombre(generoDTO.getNombre());
        genero.setPeliculas(generoDTO.getPeliculas());
        genero.setSeries(generoDTO.getSeries());
        return genero;
    }
}
