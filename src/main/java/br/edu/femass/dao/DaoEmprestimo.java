package br.edu.femass.dao;
import java.util.List;
import br.edu.femass.Model.Emprestimo;
public class DaoEmprestimo extends Dao<Emprestimo>{

    public List<Emprestimo> buscarTodos(){
        return em.createQuery("select e from Emprestimo e order by e.id").getResultList();

    }

}