package br.com.musicboxd.model.artistas;

import java.util.ArrayList;
import java.util.List;

import br.com.musicboxd.model.avaliacoes.AvaliacaoCantor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cantores")
public class Cantor extends Artista {
    private int idade;
    @OneToMany(mappedBy = "cantor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvaliacaoCantor> avaliacoes = new ArrayList<>();

    public Cantor(String nome, String generoMusical, int idade) {
        super(nome, generoMusical);
        this.idade = idade;
    }

    public Cantor() {}

    public int getIdade() {
        return idade;
    }
}
