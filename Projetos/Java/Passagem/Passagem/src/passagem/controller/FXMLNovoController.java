/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passagem.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 FXML Controller class

 @author Petrobras
 */
public class FXMLNovoController implements Initializable {

    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private Button btSalvarHtml;
    @FXML
    private Button btHtmlTxt;
    @FXML
    private AnchorPane apNovo;

    /**
     Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void dragOverHTML(DragEvent event) {
        System.out.println("Drag detected");
    }

    @FXML
    private void btSalvarHtmlClick() {

        System.out.println("Clicado em save");

        //Cria uma String para armazenar o conteúdo escrito pelo usuário
        String stringHtml = this.btHtmlClick();

        //Constrói um diálogo para o usuário salvar o arquivo
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        //Seta o diretório inicial
        fileChooser.setInitialDirectory(new File("E:\\Adam\\Projetos\\Java\\Passagem\\Saves"));
        //Encontra o stage da ação atual
        Stage stage = (Stage) apNovo.getScene().getWindow();
        //Abre a caixa de diálogo para deixar o usuário escolher o local de salvar
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                //Aloca um arquivo onde o conteúdo erá salvo
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(file);
                fileWriter.write(stringHtml);
                fileWriter.close();
            } catch (IOException ex) {
                System.err.println("Erroa ao salvar o arquivo");
            }

        }

    }

    @FXML
    private String btHtmlClick() {
        //Pega o código html original vindo do editor
        String raw = htmlEditor.getHtmlText();
        int posAtual = 0;
        
        //Realiza um loop substituindo os termos necessário até que não haja mais
        //nenhum termo a ser substituído
        while (posAtual > -1) {
            //Encontra o primeiro local do termo a href. Isto diz que provavelmente há uma 
            //imagem inserida no texto depois do fechamento desta tag
            posAtual = raw.indexOf("<a href");

            //Armazena as posições dos termos para substituição
            int localHref = raw.indexOf("href");
            int localFechamento = raw.indexOf(">", localHref);
            int localBarraA = raw.indexOf("/a");

            CharSequence cs = null;
 
            try {
                //Monta a sequencia de texto que deverá ser suprimida, pois é o texto padrão inserido
                //automaticamente quando uma imagem é inserida
                cs = raw.subSequence(localFechamento, localBarraA);
                
                //Começam as modificações
                raw = raw.replaceFirst("a href", "img src");
                raw = raw.replaceFirst("</a>", "");
                raw = raw.replace(cs, "><");
            } catch (Exception e) {
                System.err.println("Erro na substiutiçao de texto");
            }
        }
        System.out.println("HTML MODIFICADO: " + raw);
        return raw;
    }

}
