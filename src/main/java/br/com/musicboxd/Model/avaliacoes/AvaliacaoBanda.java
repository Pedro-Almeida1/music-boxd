package br.com.musicboxd.Model.avaliacoes;

import br.com.musicboxd.Model.Usuario;
import br.com.musicboxd.Model.artistas.Banda;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacoes_bandas")
public class AvaliacaoBanda extends Avaliacao{
    
    @ManyToOne(optional = false)
    private Banda banda;

    public AvaliacaoBanda() {}
    
    public AvaliacaoBanda(Usuario usuario, double nota, Banda banda) {
        super(usuario, nota);
        this.banda = banda;
    }
	
    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}
