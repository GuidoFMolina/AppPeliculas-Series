package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {
    public GeneroDTO saveGender(GeneroDTO gender);
    public List<GeneroDTO> getAllsGender();
    public GeneroDTO getGenderById(Long id);
    public GeneroDTO updateGender(GeneroDTO gender, Long id);
    public void deleteGender(Long id);
}
