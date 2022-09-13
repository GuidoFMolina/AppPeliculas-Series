package com.demo.AppPeliculas.Series.repository;

import com.demo.AppPeliculas.Series.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsernameOrEmail(String username, String email);
    public Optional<User> findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
