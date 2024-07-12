package com.uninter.biblioteca.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uninter.biblioteca.model.dao.EmprestimoDaoImpl;
import com.uninter.biblioteca.model.dao.LivroDaoImpl;
import com.uninter.biblioteca.model.dao.UsuarioDaoImpl;
import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.model.entity.Usuario;
import org.springframework.stereotype.Service;

// Ana Leticia Vieira Reis de Carvalho
// RU: 4106985

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.ParseException;

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
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        Emprestimo novoEmprestimo = new Emprestimo();

        Usuario usuario = usuarioDao.findById(emprestimo.getUsuario().getId());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Livro livro = livroDao.findById(emprestimo.getLivro().getId());
        if(livro==null) {
            throw new RuntimeException("Livro não encontrado");
        }

        if (livro.getDisponibilidade() == INDISPONIVEL) {
            throw new RuntimeException("Livro indisponível para emprestimo");
        }

        if (emprestimo.getData_emprestimo() != null) {
            dataEmprestimo = emprestimo.getData_emprestimo();
        } else {
            dataEmprestimo = new Date();
        }

        if (emprestimo.getData_devolucao() != null) {
           dataDevolucao = emprestimo.getData_devolucao();
        }

        novoEmprestimo.setData_emprestimo(dataEmprestimo);
        novoEmprestimo.setData_devolucao(dataDevolucao);
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setStatus(PENDENTE);
        livro.setDisponibilidade(INDISPONIVEL);

        emprestimoDao.save(novoEmprestimo);
        return novoEmprestimo;
    }

    // método para atualizar um emprestimo
    public Emprestimo atualizarEmprestimo(Long id, Emprestimo emprestimo) {
        Emprestimo emprestimoExistente = emprestimoDao.findById(id);
        if (emprestimoExistente == null) {
           throw new RuntimeException("Emprestimo não encontrado com o ID: " + id);
        }

        if (emprestimo.getUsuario() != null) {
            Usuario usuario = usuarioDao.findById(emprestimo.getUsuario().getId());
            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado");
            }
            emprestimoExistente.setUsuario(usuario);
        }

        if (emprestimo.getLivro() != null) {
            Livro livro = livroDao.findById(emprestimo.getLivro().getId());
            if (livro == null) {
               throw new RuntimeException("Livro não encontrado");
            }
            emprestimoExistente.setLivro(livro);
        }

        if (emprestimo.getData_emprestimo() != null) {
            dataEmprestimo = emprestimo.getData_emprestimo();
            emprestimoExistente.setData_emprestimo(dataEmprestimo);
        }

        if (emprestimo.getData_devolucao() != null) {
            dataDevolucao = emprestimo.getData_devolucao();
            emprestimoExistente.setData_devolucao(dataDevolucao);
        }

        if (emprestimo.getStatus() != null) {
            emprestimoExistente.setStatus(emprestimo.getStatus());
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
