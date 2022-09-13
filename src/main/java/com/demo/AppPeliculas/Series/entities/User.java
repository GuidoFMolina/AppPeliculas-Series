package com.demo.AppPeliculas.Series.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nombre", length = 100)
    private String name;
    @Column(name = "Email", length = 100)
    private String email;
    @Column(name = "username", length = 100)
    private String username;
    @Column(name = "password", length = 100)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UsuarioRoles",
            joinColumns=@JoinColumn(name="idUsuario", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="idRol", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
