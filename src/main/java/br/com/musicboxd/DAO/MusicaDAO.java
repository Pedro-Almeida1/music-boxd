package br.com.musicboxd.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Musica;

public class MusicaDAO extends GenericDAO<Musica> {

    public MusicaDAO() {
        super(Musica.class);
    }

    public List<Musica> buscarPorNome(String nome) {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            Query<Musica> query = sessao.createQuery("FROM Musica m WHERE m.nome LIKE :nome", Musica.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.list();
        }
    }
    
}
