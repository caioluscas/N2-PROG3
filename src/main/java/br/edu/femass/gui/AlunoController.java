package br.edu.femass.gui;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.Model.Aluno;
import br.edu.femass.dao.DaoAluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AlunoController implements Initializable {

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtMatricula;

    @FXML
    private ListView<Aluno> LstAluno;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private TableView <Aluno> tableAluno = new TableView<Aluno>();

    @FXML
    private TableColumn <Aluno, Long> colID = new TableColumn<>();

    @FXML
    private TableColumn <Aluno, String> colNome = new TableColumn<>();

    @FXML
    private TableColumn <Aluno, String> colEndereco = new TableColumn<>();

    @FXML
    private TableColumn <Aluno, String> colTelefone = new TableColumn<>();

    @FXML
    private TableColumn <Aluno, String> colMatricula = new TableColumn<>();

    private DaoAluno dao = new DaoAluno();

    private Aluno aluno;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {

        
        aluno.setNome(TxtNome.getText());
        aluno.setEndereco(TxtEndereco.getText()); 
        aluno.setTelefone(TxtTelefone.getText());
        aluno.setMatricula(TxtMatricula.getText());
         
        if(incluindo){
            dao.inserir(aluno);
        }else{
            dao.alterar(aluno);
        }   
        preencherLista();
        preencherTabela();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;

        aluno = new Aluno();
        TxtNome.setText("");
        TxtEndereco.setText("");
        TxtTelefone.setText("");
        TxtMatricula.setText("");
        TxtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);
        preencherLista();
        preencherTabela();
    }

    @FXML
    private void LstAluno_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void LstAluno_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void editar(boolean habilitar) {
        LstAluno.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtMatricula.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {
        this.aluno = LstAluno.getSelectionModel().getSelectedItem();

        if (aluno == null)
            return;

        TxtNome.setText(aluno.getNome());
        TxtEndereco.setText(aluno.getEndereco());
        TxtTelefone.setText(aluno.getTelefone());
        TxtMatricula.setText(aluno.getMatricula());
    }

    private void preencherLista(){
        List<Aluno> alunos = dao.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        LstAluno.setItems(data);
    }

    private void preencherTabela(){
        List<Aluno> alunos = dao.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tableAluno.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(new PropertyValueFactory<Aluno,Long>("ID"));
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno,String>("nome"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<Aluno,String>("endereco"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<Aluno,String>("telefone"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Aluno,String>("matricula"));
        preencherLista();
        preencherTabela();
    }
}
