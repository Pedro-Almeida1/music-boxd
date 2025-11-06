package br.com.musicboxd;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.SwingUtilities;

import br.com.musicboxd.View.TelaLogin;
import br.com.musicboxd.config.HibernateUtil;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Aplicação iniciada!");
        
        HibernateUtil.getSessionFactory();
        
        SwingUtilities.invokeLater(() -> {
            new TelaLogin();
        });
    }
}
















