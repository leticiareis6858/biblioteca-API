package com.uninter.biblioteca.model.entity;

import com.uninter.biblioteca.model.enumeration.Disponibilidade;
import jakarta.persistence.*;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// tabela livro
@Entity
@Table(name="LIVRO")
public class Livro {

    // colunas da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name="titulo", nullable=false, length=150)
    private String titulo;

    @Column(name="autor", nullable=false, length=100)
    private String autor;

    @Column(name="isbn", nullable=false, length=17, unique = true)
    private String isbn;

    @Column(name="genero", nullable=false, length=60)
    private String genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidade",nullable = false)
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

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
