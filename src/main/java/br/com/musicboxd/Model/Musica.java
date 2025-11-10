package br.com.musicboxd.Model;

import java.util.ArrayList;
import java.util.List;

public class Musica {

    private String titulo;
    private int anoLancamento;
    private String genero;
    private int duracaoMinutos;

    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Musica(String titulo, String musico, int anoLancamento, String genero, int duracao) {
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
                titulo, anoLancamento, genero, duracaoMinutos
        );
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
