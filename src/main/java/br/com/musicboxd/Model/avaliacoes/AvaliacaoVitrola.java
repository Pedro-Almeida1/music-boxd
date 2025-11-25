package br.com.musicboxd.model.avaliacoes;

import br.com.musicboxd.model.Usuario;
import br.com.musicboxd.model.Vitrola;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacoes_vitrolas")
public class AvaliacaoVitrola extends Avaliacao{
    
    @ManyToOne(optional = false)
    private Vitrola vitrola;

    public AvaliacaoVitrola() {}
    
    public AvaliacaoVitrola(Usuario usuario, double nota, Vitrola vitrola) {
        super(usuario, nota);
        this.vitrola = vitrola;
    }
	
    public Vitrola getVitrola() {
        return vitrola;
    }

    public void setBanda(Vitrola vitrola) {
        this.vitrola = vitrola;
    }
}
