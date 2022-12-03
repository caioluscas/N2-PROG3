package br.edu.femass.gui;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import br.edu.femass.Model.Professor;
import br.edu.femass.dao.DaoProfessor;
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

public class ProfessorController implements Initializable {

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtEndereco;

    @FXML
    private TextField TxtTelefone;

    @FXML
    private TextField TxtDisciplina;

    @FXML
    private ListView<Professor> LstProfessor;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    private DaoProfessor dao = new DaoProfessor();

    private Professor professor;

    private Boolean incluindo;

    @FXML
    private void gravar_click(ActionEvent event) {

        professor.setNome(TxtNome.getText());
        professor.setEndereco(TxtEndereco.getText()); 
        professor.setTelefone(TxtTelefone.getText());
        professor.setDisciplina(TxtDisciplina.getText());
         
        if(incluindo){
            dao.inserir(professor);
        }else{
            dao.alterar(professor);
        }   
        preencherLista();
        editar(false);
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        incluindo = true;

        professor = new Professor();
        TxtNome.setText("");
        TxtEndereco.setText("");
        TxtTelefone.setText("");
        TxtDisciplina.setText("");
        TxtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(professor);
        preencherLista();
    }

    @FXML
    private void LstProfessor_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void LstProfessor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void editar(boolean habilitar) {
        LstProfessor.setDisable(habilitar);
        TxtNome.setDisable(!habilitar);
        TxtEndereco.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        TxtDisciplina.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void exibirDados() {
        this.professor = LstProfessor.getSelectionModel().getSelectedItem();

        if (professor == null)
            return;

        TxtNome.setText(professor.getNome());
        TxtEndereco.setText(professor.getEndereco());
        TxtTelefone.setText(professor.getTelefone());
        TxtDisciplina.setText(professor.getDisciplina());
    }

    private void preencherLista(){
        List<Professor> professores = dao.buscarTodos();
        ObservableList<Professor> data = FXCollections.observableArrayList(professores);
        LstProfessor.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
    }
}
