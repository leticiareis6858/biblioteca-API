package com.uninter.biblioteca.service;

import com.uninter.biblioteca.controller.dto.EmprestimoDTO;
import com.uninter.biblioteca.model.dao.EmprestimoDao;
import com.uninter.biblioteca.model.dao.LivroDao;
import com.uninter.biblioteca.model.dao.UsuarioDao;
import com.uninter.biblioteca.model.entity.Emprestimo;
import com.uninter.biblioteca.model.entity.Livro;
import com.uninter.biblioteca.model.entity.Usuario;
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

    private final EmprestimoDao emprestimoDao;

    private final LivroDao livroDao;

    private final UsuarioDao usuarioDao;

    public EmprestimoService(EmprestimoDao emprestimoDao, LivroDao livroDao, UsuarioDao usuarioDao) {
        this.emprestimoDao = emprestimoDao;
        this.livroDao = livroDao;
        this.usuarioDao = usuarioDao;
    }

    public Emprestimo criarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Usuario usuario = usuarioDao.findById(emprestimoDTO.getUsuario_id());
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Livro livro = livroDao.findById(emprestimoDTO.getLivro_id());
        if (livro == null || livro.getDisponibilidade() == INDISPONIVEL) {
            throw new RuntimeException("Livro não encontrado ou indisponível para emprestimo");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatus(PENDENTE);
        livro.setDisponibilidade(INDISPONIVEL);

        emprestimo.setData_emprestimo(formataData(emprestimoDTO.getDataEmprestimo(), new Date()));
        emprestimo.setData_devolucao(formataData(emprestimoDTO.getDataDevolucao(), null));

        return emprestimoDao.save(emprestimo);
    }

    private Date formataData(String dateStr, Date defaultDate) {
        if (dateStr == null || dateStr.isEmpty()) {
            return defaultDate;
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido", e);
        }
    }

    public Emprestimo atualizarEmprestimo(Long id, Emprestimo emprestimo) {
        Emprestimo emprestimoExistente = emprestimoDao.findById(id);

        if (emprestimoExistente != null) {

            Usuario usuarioExistente = usuarioDao.findById(emprestimo.getUsuario().getId());
            if (usuarioExistente != null) {

                Livro livroExistente = livroDao.findById(emprestimo.getLivro().getId());
                if (livroExistente != null) {
                    emprestimoExistente.setLivro(emprestimo.getLivro());
                } else {
                    throw new RuntimeException("Livro não encontrado");
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

                return emprestimoDao.save(emprestimoExistente);
            } else {
                throw new RuntimeException("Usuário não encontrado");
            }
        } else {
            throw new RuntimeException("Emprestimo não encontrado com o ID: " + id);
        }

    }

    public Emprestimo devolverEmprestimo(Long id) {
        Emprestimo emprestimoDevolvido = emprestimoDao.findById(id);

        if (emprestimoDevolvido != null) {
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

            return emprestimoDao.save(emprestimoDevolvido);
        } else {
            throw new RuntimeException("Emprestimo não encontrado");
        }
    }

    public void removerEmprestimo(Long id) {
        emprestimoDao.delete(id);
    }

    public Emprestimo obterEmprestimoPorId(Long id) {
        Emprestimo emprestimoExistente = emprestimoDao.findById(id);
        if (emprestimoExistente != null) {
            return emprestimoExistente;
        } else {
            throw new RuntimeException("Emprestimo não encontrado com o ID: " + id);
        }
    }

    public List<Emprestimo> obterTodosEmprestimos() {
        return emprestimoDao.findAll();
    }

}
