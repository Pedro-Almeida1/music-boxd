package br.com.musicboxd.Model;
import java.util.ArrayList;

public class Artista {
    String nome;
    ArrayList<String> musicas = new ArrayList<>();

    void adicionarMusica(String musica) {
        musicas.add(musica);
    }

    void verMusicas() {
        System.out.println("Músicas de " + nome + ":");
        if (musicas.isEmpty()) {
            System.out.println("(Nenhuma música cadastrada)");
        } else {
            for (String m : musicas) {
                System.out.println("- " + m);
            }
        }
    }
}

