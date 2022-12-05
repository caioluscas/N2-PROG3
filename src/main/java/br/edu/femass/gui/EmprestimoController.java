package br.edu.femass.gui;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.Model.Aluno;
import br.edu.femass.Model.Emprestimo;
import br.edu.femass.Model.Exemplar;
import br.edu.femass.Model.Leitor;
import br.edu.femass.Model.Livro;
import br.edu.femass.Model.Professor;
import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.dao.DaoProfessor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EmprestimoController implements Initializable {

    @FXML
    private Button BtnAluno;

    @FXML
    private Button BtnProfessor;

    @FXML
    private TableView<Emprestimo> tableEmprestimo = new TableView<Emprestimo>();

    @FXML
    private TableColumn<Emprestimo,Long> colIDEmprestimo;

    @FXML
    private TableColumn<Leitor, String> colLeitorEmprestimo;

    @FXML
    private TableColumn<Exemplar, String> colExemplarEmprestimo;

    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmprestimo;
    
    @FXML
    private TableView<Exemplar> tableDevolucao = new TableView<Exemplar>();

    @FXML
    private TableColumn<Exemplar, Long> colID;

    @FXML
    private TableColumn<Livro, String> colExemplar;

    @FXML
    private TableColumn<Exemplar, LocalDate> colData;

    @FXML
    private ComboBox<Aluno> cboAluno;

    @FXML
    private ComboBox<Professor> cboProfessor;


    private DaoLivro daoLivro = new DaoLivro();

    private DaoExemplar daoExemplar = new DaoExemplar();

    private DaoAluno daoAluno = new DaoAluno();

    private DaoProfessor daoProfessor = new DaoProfessor();

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    
    private Livro livro;

    private Leitor leitor;

    private Exemplar exemplar;

    private Emprestimo emprestimo;

    private Aluno aluno;

    private Professor professor;


    @FXML
    private void aluno_click(ActionEvent event) {

        preencherCombo();

        exemplar = tableDevolucao.getSelectionModel().getSelectedItem();
        aluno = cboAluno.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar,aluno);

        daoEmprestimo.inserir(emprestimo);

        preencherTabela();
    }


    @FXML
    private void professor_click(ActionEvent event) {

        preencherCombo();

        exemplar = tableDevolucao.getSelectionModel().getSelectedItem();
        professor = cboProfessor.getSelectionModel().getSelectedItem();

        emprestimo = new Emprestimo(exemplar,professor);

        daoEmprestimo.inserir(emprestimo);

        preencherTabela();
    }

    private void preencherCombo() {
        List<Aluno> alunos = daoAluno.buscarTodos();

        ObservableList<Aluno> dataAluno = FXCollections.observableArrayList(alunos);
        cboAluno.setItems(dataAluno);

        List<Professor> professores = daoProfessor.buscarTodos();

        ObservableList<Professor> dataProfessor = FXCollections.observableArrayList(professores);
        cboProfessor.setItems(dataProfessor);
        

    }

    private void preencherTabela() {
        List<Exemplar> exemplares = daoExemplar.buscarTodos();

        ObservableList<Exemplar> dataExemplar = FXCollections.observableArrayList(exemplares);
        tableDevolucao.setItems(dataExemplar);

        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();
        
        ObservableList<Emprestimo> dataEmprestimo = FXCollections.observableArrayList(emprestimos);
        tableEmprestimo.setItems(dataEmprestimo);
    }



   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIDEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo,Long>("id"));
        colLeitorEmprestimo.setCellValueFactory(new PropertyValueFactory<Leitor,String>("leitor"));
        colExemplarEmprestimo.setCellValueFactory(new PropertyValueFactory<Exemplar,String>("exemplar"));
        colDataEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo,LocalDate>("dataPrevisaoDevolucao"));
        
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar,Long>("id"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Livro,String>("livro"));
        colData.setCellValueFactory(new PropertyValueFactory<Exemplar,LocalDate>("dataAquisicao"));
        emprestimo = new Emprestimo();
        preencherTabela();
        preencherCombo();
    }
}
