package passagem.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import passagem.model.Passagem;
import passagem.model.auxiliar.Xml;

public class fxmlPrincipalController implements Initializable {

    /** ************************************************************************
     FXML
     ************************************************************************ */
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Label lbUser, lbDataTop;
    @FXML
    private TableView<Passagem> tabelaPassagens = new TableView<Passagem>();
    @FXML
    private TableColumn<Passagem, String> colAssunto = new TableColumn();
    @FXML
    private TableColumn<Passagem, String> colTipo = new TableColumn();
    @FXML
    private TableColumn<Passagem, String> colAutor = new TableColumn();
    @FXML
    private TableColumn<Passagem, String> colData = new TableColumn();
    @FXML
    private TableColumn<Passagem, String> colStatus = new TableColumn();
    @FXML
    private Accordion accordionTabela = new Accordion();
    @FXML
    private TitledPane tituloAcordion1 = new TitledPane();
    @FXML
    private ImageView ivNovo;
    @FXML
    private Label label;
    @FXML
    private Button btTrocarUsuario;
    @FXML
    private Button btPesquisar;
    @FXML
    private RadioButton rbAssunto;
    @FXML
    private RadioButton rbAutor;
    @FXML
    private RadioButton rbData;

    /** ************************************************************************
     Variáveis Locais
     ************************************************************************ */
    public fxmlPrincipalController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.trataUsuario();
            this.montaAccordion();
            this.tratRadioButtons();
            Image img = new Image("/imagens/novo.jpg");
            this.ivNovo.setImage(img);
            new Xml();
        } catch (SAXException ex) {
            Logger.getLogger(fxmlPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ivNovoClick() {
        System.out.println("Teste de click");
        try {
            Parent root1 = FXMLLoader.load(getClass().getResource("/passagem/view/fxmlNovo.fxml"));
            Stage stage = new Stage();
            //stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btTrocarUsuarioClick() {

        if (this.txtUser.getText() == null || this.txtUser.getText().isEmpty()) {
            this.lbUser.setText(System.getProperty("user.name"));
        } else {
            this.lbUser.setText(this.txtUser.getText());
        }
    }

    @FXML
    private void btPesquisarClick() {
    }

    private String getHoraAtual() {
        String horaAtual;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        horaAtual = dtf.format(now);
        System.out.println(horaAtual);
        return horaAtual;
    }

    private void trataUsuario() {
        //Passa o nome do usuário para a Label
        this.lbUser.setText("Usuário: " + System.getProperty("user.name"));
        //Passa a data e hora para o Label
        this.lbDataTop.setText(this.getHoraAtual());
    }

    private void montaAccordion() { //Bind das colunas às propriedades do objeto Passagem
        //As propriedades são: assunto, autor, data e tipo
        //Criando uma fábrica de objeto a ser colocado dentro das células da coluna
        this.colAssunto.setCellFactory(ComboBoxTableCell.forTableColumn("xxxx", "yyy", "11111", "aaaaa"));
        this.colAssunto.setCellValueFactory(new PropertyValueFactory<Passagem, String>("assunto"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory<Passagem, String>("autor"));
        this.colData.setCellValueFactory(new PropertyValueFactory<Passagem, String>("data"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<Passagem, String>("tipo"));

        //Instanciação dos objetos e passagem dos mesmos a um array
        ArrayList<Passagem> lista = new ArrayList<>();
        lista.add(new Passagem("assunto1", "autor1", "data1", "tipo1"));
        lista.add(new Passagem("assunto2", "autor2", "data1", "tipo1"));
        lista.add(new Passagem("assunto3", "autor3", "data2", "tipo1"));
        lista.add(new Passagem("assunto4", "autor4", "data3", "tipo2"));
        lista.add(new Passagem("assunto5", "autor5", "data3", "tipo2"));

        //Tornando a lista observável
        ObservableList<Passagem> listaDeClientes = FXCollections.observableArrayList(lista);

        //Passando a lista para a tabela
        tabelaPassagens.setItems(listaDeClientes);

        //Teste accordion - Inserindo Titleds
        for (int i = 0; i < 10; i++) {
            TitledPane tp = new TitledPane();
            tp.setText(String.valueOf(i + 1));
            //Inserindo um AnchorPane como conteúdo a cada titled pane
            AnchorPane ap = new AnchorPane();
            //Inserindo um TableView dentro do AnchorPane
            TableView tv = new TableView();
            ap.getChildren().add(tv);
            //Inserindo colunas nas tabelas
            for (int j = 1; j < 6; j++) {
                tv.getColumns().add(new TableColumn("Coluna " + String.valueOf(j)));
            }
            tp.setContent(ap);
            accordionTabela.getPanes().add(tp);
        }
    }

    private void tratRadioButtons() {
        
        //Default de seleção do resioButton
        this.rbData.selectedProperty().set(true);
        
        ToggleGroup tg = new ToggleGroup();
        this.rbAssunto.setToggleGroup(tg);
        this.rbAutor.setToggleGroup(tg);
        this.rbData.setToggleGroup(tg);

    }

}
