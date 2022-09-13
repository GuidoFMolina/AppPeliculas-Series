package com.demo.AppPeliculas.Series.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password"));
    }
}
