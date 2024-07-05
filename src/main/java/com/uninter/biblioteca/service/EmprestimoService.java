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

// classe de serviço para emprestimo
@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // define o formato de data para dd-mm-yyyy
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    // define o formato de data_emprestimo para dd-mm-yyyy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataEmprestimo;

    // define o formato de data_devolucao para dd-mm-yyyy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDevolucao;

    //método para formatar data
    private Date formataData(String dateStr){
        Date dataFormatada;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            simpleDateFormat.setLenient(false);
            dataFormatada = simpleDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataFormatada);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dataFormatada = calendar.getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido.", e);
        }
        return dataFormatada;
    }

    // método para criar um emprestimo
    public Emprestimo criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();

        Usuario usuario = usuarioRepository.findById(emprestimoDTO.getUsuario_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(emprestimoDTO.getLivro_id())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getDisponibilidade() == INDISPONIVEL) {
            throw new RuntimeException("Livro indisponível para emprestimo");
        }

        if (emprestimoDTO.getDataEmprestimo() != null && !emprestimoDTO.getDataEmprestimo().isEmpty()) {
            dataEmprestimo = formataData(emprestimoDTO.getDataEmprestimo());
        } else {
            dataEmprestimo = new Date();
        }
        emprestimo.setData_emprestimo(dataEmprestimo);

        if (emprestimoDTO.getDataDevolucao() != null && !emprestimoDTO.getDataDevolucao().isEmpty()) {
            dataDevolucao = formataData(emprestimoDTO.getDataDevolucao());
            emprestimo.setData_devolucao(dataDevolucao);
        }

        emprestimo.setData_emprestimo(dataEmprestimo);
        emprestimo.setData_devolucao(dataDevolucao);
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatus(PENDENTE);
        livro.setDisponibilidade(INDISPONIVEL);

        return emprestimoRepository.save(emprestimo);
    }

    // método para atualizar um emprestimo
    public Emprestimo atualizarEmprestimo(Long id, EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emprestimo não encontrado com o ID: " + id));

        if (emprestimoDTO.getUsuario_id() != null) {
            Usuario usuario = usuarioRepository.findById(emprestimoDTO.getUsuario_id())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            emprestimoExistente.setUsuario(usuario);
        }

        if (emprestimoDTO.getLivro_id() != null) {
            Livro livro = livroRepository.findById(emprestimoDTO.getLivro_id())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
            emprestimoExistente.setLivro(livro);
        }

        if (emprestimoDTO.getDataEmprestimo() != null) {
            dataEmprestimo = formataData(emprestimoDTO.getDataEmprestimo());
            emprestimoExistente.setData_emprestimo(dataEmprestimo);
        }

        if (emprestimoDTO.getDataDevolucao() != null) {
            dataDevolucao = formataData(emprestimoDTO.getDataDevolucao());
            emprestimoExistente.setData_devolucao(dataDevolucao);
        }

        if (emprestimoDTO.getStatus() != null) {
            emprestimoExistente.setStatus(emprestimoDTO.getStatus());
        }

        return emprestimoRepository.save(emprestimoExistente);
    }

    // método para devolver um emprestimo
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

    // método para excluir um emprestimo
    public void removerEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }

    // método para obter um emprestimo pelo seu id
    public Emprestimo obterEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + id));
    }

    // método para obter todos os emprestimos
    public List<Emprestimo> obterTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        if (emprestimos.isEmpty()) {
            throw new RuntimeException("Nenhum empréstimo encontrado!");
        }
        return emprestimos;
    }

}
