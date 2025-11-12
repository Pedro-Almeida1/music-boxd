package br.com.musicboxd.DAO.ArtistaDAO;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.model.artistas.Banda;

public class BandaDAO extends GenericDAO<Banda> {

    public BandaDAO(Class<Banda> entidade) {
        super(Banda.class);
    }
    
}
