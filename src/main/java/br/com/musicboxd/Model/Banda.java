package br.com.musicboxd.Model;

import java.util.ArrayList;

public class Banda {
    private String nome;
    private String generoMusical;
    private int anoFormacao; 
    private ArrayList<String> musicas;


    public Banda(String nome, String genero, int anoFormacao){
        this.nome = nome;
        this.generoMusical = genero;
        this.anoFormacao = anoFormacao;
        this.musicas = new ArrayList<>();
    }

    public void AdicionarMusica(String musica){
        musicas.add(musica);
    }

    public void removerMusicas(String musica){
        if(musicas.contains(musica)){
            musicas.remove(musica);
            System.out.println("Musica removida com sucesso");
        }else{
            System.out.println("A musica não foi encontrada na lista");
        }
    }

    public void VerMusicas(){
        System.out.println("Musicas de " + nome + ":");
        if(musicas.isEmpty()){
            System.out.println("Não há músicas para essa banda!");
        }else{
            for(String m : musicas){
                System.out.println("- " + m);
            }
        }
    }
}
