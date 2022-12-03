package br.edu.femass.gui;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ExemplarController implements Initializable {

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
    private TableView<Exemplar> tabelaExemplar = new TableView<Exemplar>();
    
    @FXML
    private TableColumn<Exemplar, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar, LocalDate> colData = new TableColumn();

    @FXML
    private TableColumn<Livro, String> colLivro = new TableColumn<>();

    private DaoLivro daoLivro = new DaoLivro();

    private DaoExemplar daoExemplar = new DaoExemplar();

    private Livro livro;

    private Exemplar exemplar;

    private Boolean incluindo;


    @FXML
    private void gravar_click(ActionEvent event) {

         
        if(incluindo){
            daoExemplar.inserir(exemplar);
        }else{
            daoExemplar.alterar(exemplar);
        }
        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;

        livro = new Livro();
        exemplar = new Exemplar();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        daoExemplar.apagar(exemplar);
        preencherTabela();
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
        tabelaExemplar.setDisable(habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {
        this.exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();

        if (exemplar == null)
            return;
    }

    private void preencherTabela(){
        List<Exemplar> exemplares = daoExemplar.buscarTodos();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabelaExemplar.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Exemplar, Long>("id"));
        colLivro.setCellValueFactory(new PropertyValueFactory<Livro, String>("Livro"));
        colData.setCellValueFactory(new PropertyValueFactory<Exemplar, LocalDate>("Data"));
        preencherTabela();
    }
}
