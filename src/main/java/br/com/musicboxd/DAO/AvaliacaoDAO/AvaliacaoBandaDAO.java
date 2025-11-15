package br.com.musicboxd.DAO.AvaliacaoDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Banda;
import br.com.musicboxd.model.avaliacoes.AvaliacaoBanda;

public class AvaliacaoBandaDAO extends GenericDAO<AvaliacaoBanda>{

    public AvaliacaoBandaDAO() {
        super(AvaliacaoBanda.class);
    }
    
	public List<AvaliacaoBanda> buscaPorBanda(Banda banda) {
		try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
			return sessao
					.createQuery("""
							FROM AvaliacaoBanda a
							  				WHERE a.banda.id = :bandaId
							""", AvaliacaoBanda.class)
					.setParameter("bandaId", banda.getId())
					.list();
		}
	}

		public void salvarOuAtualizar(Banda banda, Usuario usuario, double nota) {
		Transaction transacao = null;
		try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
			transacao = sessao.beginTransaction();
			var avaliacao = sessao
					.createQuery("""
							FROM AvaliacaoBanda a
							WHERE a.banda.id = :bandaId AND
							a.usuario.id = :usuarioId
							""",
							AvaliacaoBanda.class)
					.setParameter("bandaId", banda.getId())
					.setParameter("usuarioId", usuario.getId())
					.uniqueResult();

			if (avaliacao != null) {
				avaliacao.setNota(nota);
			} else {
				avaliacao = new AvaliacaoBanda(usuario, nota, banda);
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
