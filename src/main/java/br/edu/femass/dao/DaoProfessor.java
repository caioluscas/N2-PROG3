package br.edu.femass.dao;

import java.util.List;


import br.edu.femass.Model.Professor;

public class DaoProfessor extends Dao<Professor>{

    public List<Professor> buscarTodos(){
        return em.createQuery("select a from Professor a order by a.nome").getResultList();

    }

}