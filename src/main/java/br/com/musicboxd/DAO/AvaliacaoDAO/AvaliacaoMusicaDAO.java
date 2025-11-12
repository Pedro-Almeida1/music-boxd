package br.com.musicboxd.DAO.AvaliacaoDAO;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.model.avaliacoes.AvaliacaoMusica;

public class AvaliacaoMusicaDAO extends GenericDAO<AvaliacaoMusica> {

    public AvaliacaoMusicaDAO() {
        super(AvaliacaoMusica.class);
    }
    
}
