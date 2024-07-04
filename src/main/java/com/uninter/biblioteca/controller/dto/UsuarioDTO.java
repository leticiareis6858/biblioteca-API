package com.uninter.biblioteca.controller.dto;

import com.uninter.biblioteca.model.enumeration.Cargo;

// classe que representa o DTO de usuario
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private Cargo cargo;

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}