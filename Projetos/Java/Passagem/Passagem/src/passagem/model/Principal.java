/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passagem.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Petrobras
 */
public class Principal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        /*Define o local onde está o FXML. Iniciar com barra assume que ates
         * da barra o diretório seja scr/ */
        Parent root = FXMLLoader.load(getClass().getResource("/passagem/view/fxmlPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/imagens/iconeReport.png"));
        stage.setTitle("Passagem de Serviço da Automação de P74 - v0");
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
