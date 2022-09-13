package com.demo.AppPeliculas.Series.service;

import com.demo.AppPeliculas.Series.dto.SerieDTO;
import com.demo.AppPeliculas.Series.dto.SerieResponse;
import com.demo.AppPeliculas.Series.dto.SerieSlimDTO;

import java.util.List;


public interface SerieService {
    public SerieDTO createSerie(SerieDTO serieDTO);
    public SerieResponse getAllSeries(Integer pageNo, Integer pageSize, String sortBy, String sortOrder);
    public List<SerieSlimDTO> getSerieByName(String name);
    public List<SerieSlimDTO> getSerieByYear(Integer year);
    public List<SerieSlimDTO> getSerieByGender(Long idGender);
    public SerieDTO getSerieById(long id);
    public SerieDTO updateSerie(SerieDTO serieDTO, long idSerie);
    public SerieDTO addCharacterToSerie(Long idSerie, Long idCharacter);
    public void deleteSerie(Long id);
    public SerieDTO deleteCharacterToSerie(Long idSerie, Long idCharacter);
}
