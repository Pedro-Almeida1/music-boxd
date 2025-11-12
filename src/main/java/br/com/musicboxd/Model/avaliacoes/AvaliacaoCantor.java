package br.com.musicboxd.model.avaliacoes;

import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.artistas.Cantor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacoes_cantores")
public class AvaliacaoCantor extends Avaliacao{
    
    @ManyToOne(optional = false)
    private Cantor cantor;

    public AvaliacaoCantor() {}
    
    public AvaliacaoCantor(Usuario usuario, double nota, Cantor cantor) {
        super(usuario, nota);
        this.cantor = cantor;
    }
	
    public Cantor getCantor() {
        return cantor;
    }

    public void setCantor(Cantor cantor) {
        this.cantor = cantor;
    }
}
