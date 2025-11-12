package br.com.musicboxd;

import javax.swing.SwingUtilities;

import br.com.musicboxd.config.HibernateUtil;
import br.com.musicboxd.view.TelaLogin;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Aplicação iniciada!");
        
       // HibernateUtil.getSessionFactory();
        
        SwingUtilities.invokeLater(() -> {
        	// new TelaCadastroMusica();
           new TelaLogin();
        });
    }
}
















