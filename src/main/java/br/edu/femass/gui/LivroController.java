package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.Model.Autor;
import br.edu.femass.Model.Livro;
import br.edu.femass.dao.DaoAutor;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivroController implements Initializable {

    @FXML
    private TextField TxtTitulo;

    @FXML
    private ListView<Livro> LstLivro;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private ComboBox<Autor> cboAutor;

    @FXML
    private TableView <Livro> tableLivro = new TableView<Livro>();

    @FXML
    private TableColumn <Livro, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn <Livro, String> colTitulo = new TableColumn<>();

    @FXML
    private TableColumn <Autor, String> colAutor = new TableColumn<>();

    private DaoAutor daoAutor = new DaoAutor();

    private DaoLivro dao = new DaoLivro();

    private Livro livro;

    private Autor autor;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {

        
        livro.setTitulo(TxtTitulo.getText());
        livro.setAutor(cboAutor.getSelectionModel().getSelectedItem());
        
        if(incluindo){
            dao.inserir(livro);
        }else{
            dao.alterar(livro);
        }
        preencherLista();
        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        incluindo = true;

        livro = new Livro();
        autor = new Autor();
        TxtTitulo.setText("");
        TxtTitulo.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(livro);
        preencherTabela();
        preencherLista();
    }

    @FXML
    private void LstAutor_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void LstAutor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void editar(boolean habilitar) {
        LstLivro.setDisable(habilitar);
        TxtTitulo.setDisable(!habilitar);
        cboAutor.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);

    }

    private void exibirDados() {
        this.livro = LstLivro.getSelectionModel().getSelectedItem();

        if (livro == null)
            return;

        TxtTitulo.setText(livro.getTitulo());
    }

    private void exibirTabela() {
        this.livro = tableLivro.getSelectionModel().getSelectedItem();

        if (livro == null)
            return;

        TxtTitulo.setText(livro.getTitulo());
    }

    private void preencherLista(){
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        LstLivro.setItems(data);
    }

    private void preencherTabela(){
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tableLivro.setItems(data);
    }

    private void preencherCombo(){
        List<Autor> autores = daoAutor.buscarTodos();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboAutor.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Livro,Long>("ID"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Livro,String>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Autor,String>("autor"));
        preencherLista();
        preencherCombo();
        preencherTabela();
    }
}
