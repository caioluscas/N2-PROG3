package br.edu.femass.dao;
import java.util.List;
import br.edu.femass.Model.Exemplar;


public class DaoExemplar extends Dao<Exemplar>{

    public List<Exemplar> buscarTodos(){
        return em.createQuery("select a from Exemplar a order by a.id").getResultList();

    }

}