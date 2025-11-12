package br.com.musicboxd.model.avaliacoes;

import br.com.musicboxd.model.Musica;
import br.com.musicboxd.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacoes_musicas")
public class AvaliacaoMusica extends Avaliacao{
    
    @ManyToOne(optional = false)
    private Musica musica;

    public AvaliacaoMusica() {}
    
    public AvaliacaoMusica(Usuario usuario, double nota, Musica musica) {
        super(usuario, nota);
        this.musica = musica;
    }
	
    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }
}
