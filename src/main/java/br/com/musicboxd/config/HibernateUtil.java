package br.com.musicboxd.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.musicboxd.Model.Usuario;

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

	            return config.buildSessionFactory();
	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao criar SessionFactory", e);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}