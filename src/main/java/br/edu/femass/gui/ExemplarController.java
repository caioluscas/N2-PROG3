package br.edu.femass.gui;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.procedure.ProcedureCallMemento;

import br.edu.femass.Model.Autor;
import br.edu.femass.Model.Exemplar;
import br.edu.femass.Model.Livro;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
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

public class ExemplarController implements Initializable {

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnAdicionar;

    @FXML
    private TableView<Exemplar> tableExemplar = new TableView<Exemplar>();
    
    @FXML
    private TableColumn<Exemplar, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, LocalDate> colData = new TableColumn<>();

    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();

    @FXML
    private TableColumn<Autor, String> colAutor = new TableColumn<>();

    @FXML
    private ComboBox<Livro> cboLivro;

    private DaoLivro daoLivro = new DaoLivro();

    private DaoExemplar daoExemplar = new DaoExemplar();

    private Livro livro;

    private Exemplar exemplar;

    private Boolean incluindo;


    @FXML
    private void adicionar_click(ActionEvent event) {
        exemplar.setLivro(cboLivro.getSelectionModel().getSelectedItem());
        if (incluindo) {
            daoExemplar.inserir(exemplar);
        } else {
            daoExemplar.alterar(exemplar);
        }

        preencherTabela();
        editar(false); 
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencherTabela();
        incluindo = true;

        exemplar = new Exemplar();
        cboLivro.requestFocus();
    }
    
    @FXML
    private void remover_click(ActionEvent event) {
        daoExemplar.apagar(exemplar);
        preencherTabela();
    }

    private void editar(boolean habilitar) {
        tableExemplar.setDisable(habilitar);
        cboLivro.setDisable(!habilitar);
        btnAdicionar.setDisable(habilitar);
        btnRemover.setDisable(habilitar);
    }

    private void preencherTabela(){
        List<Exemplar> exemplares = daoExemplar.buscarTodos();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tableExemplar.setItems(data);
    }

    private void preencherCombo(){
        List<Livro> livros = daoLivro.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        cboLivro.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        colData.setCellValueFactory(new PropertyValueFactory<Exemplar, LocalDate>("dataAquisicao"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("livro"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("autor"));
        preencherTabela();
        preencherCombo();
    }
}
