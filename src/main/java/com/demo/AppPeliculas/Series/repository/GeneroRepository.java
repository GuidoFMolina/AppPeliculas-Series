package com.demo.AppPeliculas.Series.repository;

import com.demo.AppPeliculas.Series.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
