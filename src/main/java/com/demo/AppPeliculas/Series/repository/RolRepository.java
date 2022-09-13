package com.demo.AppPeliculas.Series.repository;

import com.demo.AppPeliculas.Series.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByName(String name);
}
