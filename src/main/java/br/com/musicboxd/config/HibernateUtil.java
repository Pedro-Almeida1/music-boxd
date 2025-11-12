package br.com.musicboxd.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Artista;
import br.com.musicboxd.model.artistas.Banda;
import br.com.musicboxd.model.artistas.Cantor;
import br.com.musicboxd.model.avaliacoes.Avaliacao;
import br.com.musicboxd.model.avaliacoes.AvaliacaoBanda;
import br.com.musicboxd.model.avaliacoes.AvaliacaoCantor;
import br.com.musicboxd.model.avaliacoes.AvaliacaoMusica;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration config = new Configuration();

			// Carrega configurações do arquivo XML
			config.configure("hibernate.cfg.xml");

			// Substitui as variáveis com valores de ambiente
			config.setProperty("hibernate.connection.username",
					System.getenv("MYSQL_USER"));
			config.setProperty("hibernate.connection.password",
					System.getenv("MYSQL_PASSWORD"));

			config.addAnnotatedClass(Usuario.class);
			config.addAnnotatedClass(Musica.class);
			config.addAnnotatedClass(Artista.class);
			config.addAnnotatedClass(Banda.class);
			config.addAnnotatedClass(Cantor.class);
			config.addAnnotatedClass(Avaliacao.class);
			config.addAnnotatedClass(AvaliacaoMusica.class);
			config.addAnnotatedClass(AvaliacaoBanda.class);
			config.addAnnotatedClass(AvaliacaoCantor.class);

			return config.buildSessionFactory();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar SessionFactory", e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}