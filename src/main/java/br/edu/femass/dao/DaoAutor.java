package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.Model.Autor;

public class DaoAutor extends Dao<Autor>{

    public List<Autor> buscarTodos(){
        return em.createQuery("select a from Autor a order by a.nome").getResultList();

    }

}