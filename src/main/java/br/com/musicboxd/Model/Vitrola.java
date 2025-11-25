package br.com.musicboxd.model;

import jakarta.persistence.Entity;

@Entity
public class Vitrola {
    private Long id;
    private String nome;
    private String modelo;

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
