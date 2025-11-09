package br.com.musicboxd.Model;

import java.util.ArrayList;

public class Banda extends Artista{
    private String descricao; 

    public Banda(String nome, String generoMusical, String descricao){
        this.nome = nome;
    	this.generoMusical = generoMusical;
        this.descricao = descricao;
        this.musicas = new ArrayList<Musica>();
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }

}
