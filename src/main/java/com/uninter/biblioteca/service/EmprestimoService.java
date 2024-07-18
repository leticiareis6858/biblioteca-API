package com.uninter.biblioteca.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.biblioteca.model.dao.EmprestimoDaoImpl;
import com.uninter.biblioteca.model.dao.LivroDaoImpl;
import com.uninter.biblioteca.model.dao.UsuarioDaoImpl;
import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.model.entity.Usuario;
import com.uninter.biblioteca.model.entity.enumeration.Status;
import org.springframework.stereotype.Service;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.util.Map;

import static com.uninter.biblioteca.model.entity.enumeration.Disponibilidade.DISPONIVEL;
import static com.uninter.biblioteca.model.entity.enumeration.Disponibilidade.INDISPONIVEL;
import static com.uninter.biblioteca.model.entity.enumeration.Status.DEVOLVIDO;
import static com.uninter.biblioteca.model.entity.enumeration.Status.PENDENTE;

// classe de serviço para emprestimo
@Service
public class EmprestimoService {

    private final EmprestimoDaoImpl emprestimoDao;

    private final LivroDaoImpl livroDao;

    private final UsuarioDaoImpl usuarioDao;

    // define o formato de data para dd-mm-yyyy
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    // define o formato de data_emprestimo para dd-mm-yyyy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataEmprestimo;

    // define o formato de data_devolucao para dd-mm-yyyy
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDevolucao;

    public EmprestimoService(EmprestimoDaoImpl emprestimoDao, LivroDaoImpl livroDao, UsuarioDaoImpl usuarioDao) {
        this.emprestimoDao = emprestimoDao;
        this.livroDao = livroDao;
        this.usuarioDao = usuarioDao;
    }

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
    public Emprestimo criarEmprestimo(Map<String, Long> emprestimoDados) {
        Emprestimo novoEmprestimo = new Emprestimo();

        Long usuarioId = emprestimoDados.get("usuario_id");
        Long livroId = emprestimoDados.get("livro_id");

        Usuario usuario = usuarioDao.findById(usuarioId);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Livro livro = livroDao.findById(livroId);
        if (livro == null) {
            throw new RuntimeException("Livro não encontrado");
        }

        Date dataEmprestimo=formataData(new SimpleDateFormat(DATE_FORMAT).format(new Date()));

        novoEmprestimo.setData_emprestimo(dataEmprestimo);
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setStatus(PENDENTE);
        livro.setDisponibilidade(INDISPONIVEL);

        emprestimoDao.save(novoEmprestimo);
        return novoEmprestimo;
    }

    // método para atualizar um emprestimo
    public Emprestimo atualizarEmprestimo(Long id, Map<String, Object> emprestimoDados) {
        Emprestimo emprestimoExistente = emprestimoDao.findById(id);
        if (emprestimoExistente == null) {
            throw new RuntimeException("Emprestimo não encontrado com o ID: " + id);
        }

        if (emprestimoDados.containsKey("usuario_id")) {
            Long usuarioId = Long.parseLong(emprestimoDados.get("usuario_id").toString());
            Usuario usuario = usuarioDao.findById(usuarioId);
            if (usuario != null) {
                emprestimoExistente.setUsuario(usuario);
            }
        }

        if (emprestimoDados.containsKey("livro_id")) {
            Long livroId = Long.parseLong(emprestimoDados.get("livro_id").toString());
            Livro livro = livroDao.findById(livroId);
            if (livro != null) {
                emprestimoExistente.setLivro(livro);
            }
        }

        if (emprestimoDados.containsKey("data_emprestimo")) {
            Date dataEmprestimo = formataData(emprestimoDados.get("data_emprestimo").toString());
            emprestimoExistente.setData_emprestimo(dataEmprestimo);
        }

        if (emprestimoDados.containsKey("data_devolucao")) {
            Date dataDevolucao = formataData(emprestimoDados.get("data_devolucao").toString());
            emprestimoExistente.setData_devolucao(dataDevolucao);
        }

        if (emprestimoDados.containsKey("status")) {
            String statusStr = emprestimoDados.get("status").toString();
            try {
                Status status = Status.valueOf(statusStr.toUpperCase());
                emprestimoExistente.setStatus(status);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Status inválido: " + statusStr);
            }
        }

        emprestimoDao.update(emprestimoExistente);
        return emprestimoExistente;
    }

    // método para devolver um emprestimo
    public Emprestimo devolverEmprestimo(Long id) {
        Emprestimo emprestimoDevolvido = emprestimoDao.findById(id);
        if (emprestimoDevolvido == null) {
           throw new RuntimeException("Emprestimo não encontrado");
        }

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

        emprestimoDao.update(emprestimoDevolvido);
        return emprestimoDevolvido;
    }

    // método para excluir um emprestimo
    public void removerEmprestimo(Long id) {
        emprestimoDao.delete(id);
    }

    // método para obter um emprestimo pelo seu id
    public Emprestimo obterEmprestimoPorId(Long id) {
        Emprestimo emprestimoEncontrado=emprestimoDao.findById(id);
              if(emprestimoEncontrado==null){
                 throw new RuntimeException("Empréstimo não encontrado com o ID: " + id);
              }
              return emprestimoEncontrado;
    }

    // método para obter todos os emprestimos
    public List<Emprestimo> obterTodosEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoDao.findAll();
        if (emprestimos.isEmpty()) {
            throw new RuntimeException("Nenhum empréstimo encontrado!");
        }
        return emprestimos;
    }

}
