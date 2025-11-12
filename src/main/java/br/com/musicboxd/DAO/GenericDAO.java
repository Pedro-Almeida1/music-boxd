package br.com.musicboxd.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.com.musicboxd.config.HibernateUtil;

public class GenericDAO<T> {

    private final Class<T> entidade;

    public GenericDAO(Class<T> entidade) {
        this.entidade = entidade;
    }

    public void salvar(T entidade) {
        Transaction transacao = null;
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            sessao.persist(entidade);
            transacao.commit();
        } catch (Exception e) {
            if (transacao != null)
                transacao.rollback();
            throw e;
        }
    }

    public void deletar(T entidade) {
        Transaction transacao = null;
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            sessao.remove(entidade);
            transacao.commit();
        } catch (Exception e) {
            if (transacao != null)
                transacao.rollback();
            throw e;
        }
    }

    public T buscarPorId(Long id) {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            return sessao.get(entidade, id);
        }
    }

    public List<T> listarTodos() {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = sessao.createQuery("FROM " + entidade.getSimpleName(), entidade);
            return query.list();
        }
    }
}
