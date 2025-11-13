package br.com.musicboxd.DAO.AvaliacaoDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.avaliacoes.AvaliacaoMusica;

public class AvaliacaoMusicaDAO extends GenericDAO<AvaliacaoMusica> {

    public AvaliacaoMusicaDAO() {
        super(AvaliacaoMusica.class);
    }
    
    public void salvarOuAtualizar(Musica musica, Usuario usuario, double nota) {
    	Transaction transacao = null;
    	try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
    		transacao = sessao.beginTransaction();
    		var avaliacao = sessao
            		.createQuery("""
            				FROM AvaliacaoMusica a
            				WHERE a.musica.id = :musicaId AND
            				a.usuario.id = :usuarioId
            				""",
            				AvaliacaoMusica.class)
            		.setParameter("musicaId", musica.getId())
            		.setParameter("usuarioId", usuario.getId())
            		.uniqueResult();

            if (avaliacao != null) {
            	avaliacao.setNota(nota);
            } else {
            	avaliacao = new AvaliacaoMusica(usuario, nota, musica);
            	sessao.persist(avaliacao);
            }
            transacao.commit();
    } catch(Exception ex) {
    	if (transacao != null)
            transacao.rollback();
        throw ex;
    }
}
}
