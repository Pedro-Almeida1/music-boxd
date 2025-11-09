package br.com.musicboxd.Model;

public class Avaliacao {
	private Usuario usuario;
	private double nota;
	
	public Avaliacao(Usuario usuario, double nota) {
		this.usuario = usuario;
		this.nota = nota;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
}
