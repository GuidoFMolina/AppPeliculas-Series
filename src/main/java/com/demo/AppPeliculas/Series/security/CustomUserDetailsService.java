package com.demo.AppPeliculas.Series.security;

import com.demo.AppPeliculas.Series.entities.Rol;
import com.demo.AppPeliculas.Series.entities.User;
import com.demo.AppPeliculas.Series.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository  userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
            User usuario = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + usernameOrEmail));

            return new org.springframework.security.core.userdetails.
                    User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
        }
    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles) {
        return roles.stream().map((rol) -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }
}
