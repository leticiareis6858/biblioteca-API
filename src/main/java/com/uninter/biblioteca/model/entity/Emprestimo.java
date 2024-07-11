package com.uninter.biblioteca.model.entity;

import com.uninter.biblioteca.model.entity.enumeration.Status;
import jakarta.persistence.*;

import java.util.Date;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

// tabela emprestimo
@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {

    // colunas da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="livro_id", referencedColumnName = "id", nullable = false)
    private Livro livro;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emprestimo")
    private Date data_emprestimo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_devolucao")
    private Date data_devolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Date data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
