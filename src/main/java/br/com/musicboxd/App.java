package br.com.musicboxd;

import javax.swing.SwingUtilities;

import br.com.musicboxd.View.TelaLogin;

public class App {
    public static void main(String[] args) {
        System.out.println("🚀 Aplicação iniciada!");
        SwingUtilities.invokeLater(() -> {
            new TelaLogin();
        });
    }
}
