package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.Model.Aluno;


public class DaoAluno extends Dao<Aluno>{

    public List<Aluno> buscarTodos(){
        return em.createQuery("select a from Aluno a order by a.nome").getResultList();

    }

}