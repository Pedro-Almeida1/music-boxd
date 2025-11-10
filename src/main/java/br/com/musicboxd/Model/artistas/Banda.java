package br.com.musicboxd.Model.artistas;

import java.util.ArrayList;
import java.util.List;

import br.com.musicboxd.Model.avaliacoes.AvaliacaoBanda;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bandas")
public class Banda extends Artista{
    private String descricao; 
    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvaliacaoBanda> avaliacoes = new ArrayList<>(); 

    public Banda() {}

    public Banda(String nome, String generoMusical, String descricao){
        super(nome, generoMusical);
        this.descricao = descricao;
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }

}
