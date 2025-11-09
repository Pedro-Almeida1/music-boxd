package br.com.musicboxd.Model;

public class Musica {
	private String titulo;
	private int anoLancamento;
	private String genero;
	private int duracaoMinutos;
//	private double notaMusica;

	public Musica(String titulo, String musico, int anoLancamento, String genero, int duracao) {
		this.titulo = titulo;
		this.anoLancamento = anoLancamento;
		this.genero = genero;
		this.duracaoMinutos = duracao;
	}

	@Override
	public String toString() {
		return String.format(
				"Informações sobre a música\n" +
						"Nome:           	%s\n" +
						"Lançamento:		%d\n" +
						"Gênero:			%s\n" +
						"duração:	%.2f\n",
				titulo, anoLancamento, genero, duracaoMinutos);
	}
}