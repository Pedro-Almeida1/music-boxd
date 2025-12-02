package br.com.musicboxd.model;

import java.util.ArrayList;
import java.util.List;

import br.com.musicboxd.model.avaliacoes.AvaliacaoVitrola;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vitrolas")
public class Vitrola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String modelo;

    @OneToMany(mappedBy = "vitrola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvaliacaoVitrola> avaliacoes = new ArrayList<>(); 

    public Vitrola(String nome, String modelo) {
        this.nome = nome;
        this.modelo = modelo;
    }

    public Vitrola() {
    }

    public String getNome() {
        return nome;
    }

    public String getModelo() {
        return modelo;
    }

    public Long getId() {
        return id;
    }

}
