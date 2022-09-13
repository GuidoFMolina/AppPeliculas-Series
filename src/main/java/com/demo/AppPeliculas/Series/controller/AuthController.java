package com.demo.AppPeliculas.Series.controller;

import com.demo.AppPeliculas.Series.dto.LoginDTO;
import com.demo.AppPeliculas.Series.dto.RegisterDTO;
import com.demo.AppPeliculas.Series.entities.Rol;
import com.demo.AppPeliculas.Series.entities.User;
import com.demo.AppPeliculas.Series.repository.RolRepository;
import com.demo.AppPeliculas.Series.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Ha iniciado sesión correctamente", HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<String>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            return new ResponseEntity<String>("El email de usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Rol rol = rolRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(rol));
        userRepository.save(user);
        return new ResponseEntity<String>("El usuario se ha registrado correctamente", HttpStatus.OK);
    }

}

