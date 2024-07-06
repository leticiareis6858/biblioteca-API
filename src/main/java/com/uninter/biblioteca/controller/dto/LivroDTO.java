package com.uninter.biblioteca.controller.dto;

import com.uninter.biblioteca.model.enumeration.Disponibilidade;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// classe que representa o DTO de livro
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String genero;
    private Disponibilidade disponibilidade;

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

}