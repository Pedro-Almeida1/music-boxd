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

      void removerMusicas(String musica){
        if(musicas.contains(musica)){
            musicas.remove(musica);
            System.out.println("Musica removida com sucesso");
        }else{
            System.out.println("A musica não foi encontrada na lista");
        }
    }
}

