package com.uninter.biblioteca.model.entity;

import com.uninter.biblioteca.model.enums.Cargo;
import jakarta.persistence.*;

// tabela usuario
@Entity
@Table(name="USUARIO")
public class Usuario {

    // colunas da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name="nome", nullable=false, length=100)
    private String nome;

    @Column(name="email", nullable=false, unique=true, length=100)
    private String email;

    @Column(name="senha", nullable=false, length=50)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo",nullable = false)
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
