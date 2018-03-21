/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leitor.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import leitor.model.Xml;
import org.xml.sax.SAXException;

/**

 @author Petrobras
 */
public class ControleBase implements Initializable {

    @FXML
    private Label lbItensEncontrados;
    @FXML
    private AnchorPane ap0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Xml xml = new Xml();
            ArrayList<File> listaArquivosXML = xml.getListaArquivosXML();
            int numeroArquivosXML = listaArquivosXML.size();
            this.lbItensEncontrados.setText("Foram encontrados "
                    + numeroArquivosXML + " documentos xml");

            //AnchorPane raiz ap0
            //Accordion  raiz ac0
            Accordion ac0 = new Accordion();

            //Insere o ac0 no ap0
            ap0.getChildren().add(ac0);

            //Para cada arquivo encontrado é inserido um TitledPane
            for (int i = 0; i < numeroArquivosXML; i++) {
                TitledPane tp0 = new TitledPane();
                String pai = xml.getXmlr().getDocumentosPai(i);
                System.out.println("Nome do aqruivo pai__:" + pai);
                tp0.setText("Texto: " + pai);
                tp0.setMaxWidth(Double.MAX_VALUE);

//---[NIVEL 1]-------------------------------------------------------------------------------------
                //Faz primeiro subnível de painéis 
                if (xml.getListaArquivosXML().size() > 0) {

                    Accordion ac1 = null;
                    ArrayList<Accordion> arrayAc = new ArrayList<>();
                    ArrayList<TitledPane> arrayTp = new ArrayList<>();
                    //Percorre todos os nodes de primeiro subnível (N1) dos XMLs abertos
                    for (int arrayItem = 0; arrayItem < xml.getListaArquivosXML().size(); arrayItem++) {
                        //Cria um novo Accordion a cada item iterado
                        ac1 = new Accordion();
                        //Adiciona o Accordion criado a uma lista de Accordions
                        arrayAc.add(ac1);
                    }
//---[NIVEL 2]-------------------------------------------------------------------------------------

                    //Loop através dos Accordions criados anteriormente. Existe um Accordion
                    //por Node N1 encontrado em cada arquivo. O Accordion é apenas um container.
                    //Cada accordion deve ter n TitledPanes.
//                    for (int a = 0; a < xml.xmlr.procuraValor("valor3").size(); a++) {
//                        TitledPane tp = new TitledPane();
//                        String node = xml.getListaResultados().get(a).getNode();
//                        String nome = xml.getListaResultados().get(a).getNomeNode();
//                        tp.setText("<" + node + ">   [" + nome + "]");
//                        ac1.getPanes().add(tp);
//
//                    }
                    tp0.setContent(ac1);
                    ac0.getPanes().add(tp0);
                }
            }

        } catch (SAXException ex) {
            Logger.getLogger(ControleBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
