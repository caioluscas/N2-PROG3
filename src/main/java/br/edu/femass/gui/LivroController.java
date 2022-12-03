package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.Model.Autor;
import br.edu.femass.Model.Livro;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    private DaoLivro dao = new DaoLivro();

    private Livro livro;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {

        
        livro.setTitulo(TxtTitulo.getText());
         
        if(incluindo){
            dao.inserir(livro);
        }else{
            dao.alterar(livro);
        }
        preencherLista();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;

        livro = new Livro();
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

    private void preencherLista(){
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        LstLivro.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }
}
