package br.com.musicboxd.Model;

import java.util.ArrayList;

public class Cantor extends Artista {
	private int idade;
    
    public Cantor(String nome, String generoMusical, int idade){
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.musicas = new ArrayList<Musica>();
        this.idade = idade;
    }
    
    public int getIdade() {
    	return idade;
    }
}
