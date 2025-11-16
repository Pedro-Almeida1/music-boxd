package br.com.musicboxd.DAO.AvaliacaoDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Cantor;
import br.com.musicboxd.model.avaliacoes.AvaliacaoCantor;

public class AvaliacaoCantorDAO extends GenericDAO<AvaliacaoCantor>{

    public AvaliacaoCantorDAO() {
        super(AvaliacaoCantor.class);
    }
    
    public List<AvaliacaoCantor> buscaPorCantor(Cantor cantor) {
		try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
			return sessao
					.createQuery("""
							FROM AvaliacaoCantor a
							  				WHERE a.cantor.id = :cantorId
							""", AvaliacaoCantor.class)
					.setParameter("cantorId", cantor.getId())
					.list();
		}
	}

		public void salvarOuAtualizar(Cantor cantor, Usuario usuario, double nota) {
		Transaction transacao = null;
		try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
			transacao = sessao.beginTransaction();
			var avaliacao = sessao
					.createQuery("""
							FROM AvaliacaoCantor a
							WHERE a.cantor.id = :cantorId AND
							a.usuario.id = :usuarioId
							""",
							AvaliacaoCantor.class)
					.setParameter("cantorId", cantor.getId())
					.setParameter("usuarioId", usuario.getId())
					.uniqueResult();

			if (avaliacao != null) {
				avaliacao.setNota(nota);
			} else {
				avaliacao = new AvaliacaoCantor(usuario, nota, cantor);
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
