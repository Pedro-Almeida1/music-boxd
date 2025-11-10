package br.com.musicboxd.Model;
import java.util.ArrayList;
import java.util.List;

public class Artista {
    protected String nome;
    protected List<Musica> musicas = new ArrayList<Musica>();
    protected String generoMusical;

//    void adicionarMusica(Musica musica) {
//        musicas.add(musica);
//    }

    public void verMusicas() {
        System.out.println("Músicas de " + nome + ":");
        if (musicas.isEmpty()) {
            System.out.println("(Nenhuma música cadastrada)");
        } else {
            for (Musica m : musicas) {
                System.out.println(" - " + m.toString());
            }
        }
    }

      public void removerMusicas(Musica musica){
        if(musicas.contains(musica)){
            musicas.remove(musica);
            System.out.println("Musica removida com sucesso");
        } else {
            System.out.println("A musica não foi encontrada na lista");
        }
    }
      
    public String getNome() {
    	return nome;
    }
    
    public String getGeneroMusical() {
    	return generoMusical;
    }
}

