package br.edu.femass.gui;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.Model.Autor;
import br.edu.femass.dao.DaoAutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    private DaoAutor dao = new DaoAutor();

    private Autor autor;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {

        
        autor.setNome(TxtNome.getText());
        autor.setSobrenome(TxtSobrenome.getText()); 
        autor.setNacionalidade(TxtNacionalidade.getText());
         
        if(incluindo){
            dao.inserir(autor);
        }else{
            dao.alterar(autor);
        }
        preencherLista();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;

        autor = new Autor();
        TxtNome.setText("");
        TxtSobrenome.setText("");
        TxtNacionalidade.setText("");
        TxtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(autor);
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
        LstAutor.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtSobrenome.setDisable(!habilitar);
        TxtNacionalidade.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {
        this.autor = LstAutor.getSelectionModel().getSelectedItem();

        if (autor == null)
            return;

        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
        TxtNacionalidade.setText(autor.getNacionalidade());
    }

    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        LstAutor.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }
}
