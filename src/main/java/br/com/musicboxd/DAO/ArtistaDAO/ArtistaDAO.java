package br.com.musicboxd.DAO.ArtistaDAO;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.model.artistas.Artista;

public class ArtistaDAO extends GenericDAO<Artista>{
	public ArtistaDAO() {
		super(Artista.class);
	}
}
