package com.demo.AppPeliculas.Series.repository;

import com.demo.AppPeliculas.Series.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SerieRepository extends JpaRepository<Serie, Long> {
}
