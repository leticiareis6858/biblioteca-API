package com.uninter.biblioteca.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.biblioteca.controller.dto.EmprestimoDTO;
import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.repository.EmprestimoRepository;
import com.uninter.biblioteca.repository.LivroRepository;
import com.uninter.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.ParseException;

import static com.uninter.biblioteca.model.enumeration.Disponibilidade.DISPONIVEL;
import static com.uninter.biblioteca.model.enumeration.Disponibilidade.INDISPONIVEL;
import static com.uninter.biblioteca.model.enumeration.Status.DEVOLVIDO;
import static com.uninter.biblioteca.model.enumeration.Status.PENDENTE;

@Service
public class EmprestimoService {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataEmprestimo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDevolucao;

    public Emprestimo criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();

        Usuario usuario = usuarioRepository.findById(emprestimoDTO.getUsuario_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(emprestimoDTO.getLivro_id())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getDisponibilidade() == INDISPONIVEL) {
            throw new RuntimeException("Livro indisponível para emprestimo");
        }

        if (emprestimoDTO.getDataEmprestimo() == null || emprestimoDTO.getDataEmprestimo().isEmpty()) {
            dataEmprestimo = new Date();
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
                simpleDateFormat.setLenient(false);
                dataEmprestimo = simpleDateFormat.parse(emprestimoDTO.getDataEmprestimo());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataEmprestimo);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                dataEmprestimo = calendar.getTime();
            } catch (ParseException e) {
                throw new IllegalArgumentException("Formato de data inválido para data de empréstimo", e);
            }
        }
        System.out.println("Data Devolucao antes de criar: " + emprestimoDTO.getDataDevolucao());

        System.out.println("Data Devolucao depois de criar: " + emprestimoDTO.getDataDevolucao());
        if (emprestimoDTO.getDataDevolucao() != null && !emprestimoDTO.getDataDevolucao().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                sdf.setLenient(false);
                dataDevolucao = sdf.parse(emprestimoDTO.getDataDevolucao());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dataDevolucao);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                dataDevolucao = calendar.getTime();
                System.out.println("Parsed Data Devolucao depois de formatar: " + dataDevolucao);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Formato de data inválido para data de devolução", e);
            }
        }

        emprestimo.setData_emprestimo(dataEmprestimo);
        emprestimo.setData_devolucao(dataDevolucao);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatus(PENDENTE);
        livro.setDisponibilidade(INDISPONIVEL);

        return emprestimoRepository.save(emprestimo);
    }

public Emprestimo atualizarEmprestimo(Long id, Emprestimo emprestimo) {
    Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com o ID: " + id));

    if (emprestimo.getUsuario() != null) {
        usuarioRepository.findById(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        emprestimoExistente.setUsuario(emprestimo.getUsuario());
    }

    if (emprestimo.getLivro() != null) {
        livroRepository.findById(emprestimo.getLivro().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        emprestimoExistente.setLivro(emprestimo.getLivro());
    }

    if (emprestimo.getData_emprestimo() != null) {
        emprestimoExistente.setData_emprestimo(emprestimo.getData_emprestimo());
    }

    if (emprestimo.getData_devolucao() != null) {
        emprestimoExistente.setData_devolucao(emprestimo.getData_devolucao());
    }

    if (emprestimo.getStatus() != null) {
        emprestimoExistente.setStatus(emprestimo.getStatus());
    }

    return emprestimoRepository.save(emprestimoExistente);

}

    public Emprestimo devolverEmprestimo(Long id) {
        Emprestimo emprestimoDevolvido = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));

        if (emprestimoDevolvido.getStatus() == DEVOLVIDO) {
            throw new RuntimeException("Emprestimo já devolvido!");
        }

        if (emprestimoDevolvido.getLivro().getDisponibilidade() == DISPONIVEL) {
            throw new RuntimeException("Livro já devolvido!");
        }

        if (emprestimoDevolvido.getStatus() == DEVOLVIDO && emprestimoDevolvido.getLivro().getDisponibilidade() == DISPONIVEL) {
            throw new RuntimeException("Emprestimo e Livro já devolvidos!");
        }

        emprestimoDevolvido.setStatus(DEVOLVIDO);
        emprestimoDevolvido.getLivro().setDisponibilidade(DISPONIVEL);
        emprestimoDevolvido.setData_devolucao(new Date());

        return emprestimoRepository.save(emprestimoDevolvido);
    }

    public void removerEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }

    public Emprestimo obterEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public List<Emprestimo> obterTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }

}
