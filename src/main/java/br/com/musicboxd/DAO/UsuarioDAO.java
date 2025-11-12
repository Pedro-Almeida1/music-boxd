package br.com.musicboxd.DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{

    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public Usuario buscarPorNomeESenha(String nomeEmail, String senha) {
        try (Session sessao = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = sessao.createQuery("FROM Usuario u WHERE (u.nome = :nomeEmail OR u.email = :nomeEmail) AND u.senha = :senha", Usuario.class);
            query.setParameter("nomeEmail", nomeEmail);
            query.setParameter("senha", senha);
            return query.uniqueResultOptional().orElse(null);
        }
    }

}
