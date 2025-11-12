package br.com.musicboxd.model;

import java.util.ArrayList;
import java.util.List;

import br.com.musicboxd.model.artistas.Artista;
import br.com.musicboxd.model.avaliacoes.Avaliacao;
import br.com.musicboxd.model.avaliacoes.AvaliacaoMusica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int anoLancamento;
    private String genero;
    private int duracaoMinutos;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "musica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvaliacaoMusica> avaliacoes = new ArrayList<>();

    public Musica() {}

    public Musica(String titulo, int anoLancamento, String genero, int duracao) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.duracaoMinutos = duracao;
    }

    @Override
    public String toString() {
        return String.format(
                "Informações sobre a música\n"
                        + "Nome:           	%s\n"
                        + "Lançamento:		%d\n"
                        + "Gênero:			%s\n"
                        + "duração:	%d\n",
                titulo, anoLancamento, genero, duracaoMinutos);
    }

    public double calcularMediaAvaliacoes() {
        double soma = 0;
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }
        for (Avaliacao a : avaliacoes) {
            soma += a.getNota();
        }
        return soma / avaliacoes.size();
    }

}
