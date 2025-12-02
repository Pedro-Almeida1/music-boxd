package br.com.musicboxd.DAO.AvaliacaoDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.Vitrola;
import br.com.musicboxd.model.artistas.Banda;
import br.com.musicboxd.model.artistas.Cantor;
import br.com.musicboxd.model.avaliacoes.Avaliacao;
import br.com.musicboxd.model.avaliacoes.AvaliacaoBanda;
import br.com.musicboxd.model.avaliacoes.AvaliacaoCantor;
import br.com.musicboxd.model.avaliacoes.AvaliacaoMusica;
import br.com.musicboxd.model.avaliacoes.AvaliacaoVitrola;

@SuppressWarnings("rawtypes")
public class AvaliacaoDAO<T> extends GenericDAO<T> {
    private Class classeAvaliada;
    private Class avaliacaoClasse;

    public AvaliacaoDAO(Class<T> entidade) {
        super(entidade);
        avaliacaoClasse = entidade;
        switch (avaliacaoClasse.getSimpleName()) {
            case "AvaliacaoBanda":
                classeAvaliada = Banda.class;
                break;
            case "AvaliacaoCantor":
                classeAvaliada = Cantor.class;
                break;
            case "AvaliacaoMusica":
                classeAvaliada = Musica.class;
                break;
            case "AvaliacaoVitrola":
                classeAvaliada = Vitrola.class;
                break;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> buscarAvaliacoes(Long objetoId) {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            return sessao
                    .createQuery("""
                            FROM %s a
                            WHERE a.%s.id = :id
                            """.formatted(avaliacaoClasse.getSimpleName(), classeAvaliada.getSimpleName().toLowerCase()),
                            avaliacaoClasse)
                    .setParameter("id", objetoId)
                    .list();
        }
    }

    public void salvarOuAtualizar(Long objetoId, Usuario usuario, double nota, Object objeto) {
        Transaction transacao = null;
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            transacao = sessao.beginTransaction();
            Avaliacao avaliacao = sessao
                    .createQuery("""
                            FROM %s a
                            WHERE a.%s.id = :id AND
                            a.usuario.id = :usuarioId
                            """.formatted(avaliacaoClasse.getSimpleName(), classeAvaliada.getSimpleName().toLowerCase()),
                            Avaliacao.class)
                    .setParameter("id", objetoId)
                    .setParameter("usuarioId", usuario.getId())
                    .uniqueResult();

            if (avaliacao != null) {
                avaliacao.setNota(nota);
            } else {
                switch (avaliacaoClasse.getSimpleName()) {
                    case "AvaliacaoBanda":
                        avaliacao = new AvaliacaoBanda(usuario, nota, (Banda) objeto);
                        break;
                    case "AvaliacaoCantor":
                        avaliacao = new AvaliacaoCantor(usuario, nota, (Cantor) objeto);
                        break;
                    case "AvaliacaoMusica":
                        avaliacao = new AvaliacaoMusica(usuario, nota, (Musica) objeto);
                        break;
                    case "AvaliacaoVitrola":
                        avaliacao = new AvaliacaoVitrola(usuario, nota, (Vitrola) objeto);
                        break;
                }
                sessao.persist(avaliacao);
            }
            transacao.commit();
        } catch (Exception ex) {
            if (transacao != null)
                transacao.rollback();
            throw ex;
        }
    }
}
