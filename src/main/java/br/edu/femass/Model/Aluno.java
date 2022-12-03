package br.edu.femass.Model;

import javax.persistence.Entity;



@Entity
public class Aluno extends Leitor{

    private String matricula;

    public Aluno(){
        this.setPrazoMaximoDevolucao(15);
    }
    
    public Aluno(String nome, String endereco, String telefone, String matricula) {
        super(nome, endereco, telefone);
        this.matricula = matricula;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
