package br.com.musicboxd.DAO.AvaliacaoDAO;

import br.com.musicboxd.DAO.GenericDAO;
import br.com.musicboxd.model.avaliacoes.AvaliacaoCantor;

public class AvaliacaoCantorDAO extends GenericDAO<AvaliacaoCantor>{

    public AvaliacaoCantorDAO() {
        super(AvaliacaoCantor.class);
    }
    
}
