package br.edu.femass.Model;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Exemplar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataAquisicao;
    @ManyToOne(cascade = CascadeType.ALL)
    private Livro livro;



    public Exemplar(){
        this.dataAquisicao = LocalDate.now();
    }

    public Exemplar( Livro livro) {
        this.dataAquisicao = LocalDate.now();
        this.livro = livro;
       
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return ("Exemplar " + this.getLivro());
    }

    
    
}
