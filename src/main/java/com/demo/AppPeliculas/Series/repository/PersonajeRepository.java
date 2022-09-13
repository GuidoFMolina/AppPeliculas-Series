package com.demo.AppPeliculas.Series.repository;

import com.demo.AppPeliculas.Series.entities.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {

}
