package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.Model.Autor;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutorController implements Initializable {
    
    @FXML   
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;

    @FXML
    private TextField TxtNacionalidade;

    @FXML
    private ListView<Autor> LstAutor;

    private DaoAutor dao = new DaoAutor();

    //private Autor autor;

    private Boolean inserindo;
    
    @FXML
    private void gravar_click(ActionEvent event) {

        Autor autor = new Autor(
            TxtNome.getText(),
            TxtSobrenome.getText(), 
            TxtNacionalidade.getText());

         
         dao.inserir(autor);
         System.out.println(autor.getId());


    } 
    
    @FXML
    private void LstAutor_KeyPressed(KeyEvent event){

    }
   
    @FXML
    private void LstAutor_MouseClicked (MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        Autor autor = LstAutor.getSelectionModel().getSelectedItem();

        if(autor==null) return;

        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
        TxtNacionalidade.setText(autor.getNacionalidade());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Autor> autores = dao.buscarTodos();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        LstAutor.setItems(data);
    }    
}
