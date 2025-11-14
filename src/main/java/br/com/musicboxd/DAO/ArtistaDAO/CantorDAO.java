package br.com.musicboxd.DAO.ArtistaDAO;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.model.artistas.Cantor;

public class CantorDAO extends GenericDAO<Cantor> {

    public CantorDAO() {
        super(Cantor.class);
    }
    
}
