/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author creditos gonzalez
 */
public class PantallaInicioController implements Initializable {


    @FXML
    private AnchorPane pantalla;
    private TextField nombreUser;
    public static String njugador; //esta variable permite modificar el nombre
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pantalla.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #0097b2, #7ed957);");
    }    
    
    public void btnjugar(ActionEvent event) throws IOException{
        {
            try{
                njugador = nombreUser.getText();
                Parent root = FXMLLoader.load(getClass().getResource("domino.fxml"));
                Scene scene = new Scene(root);
                Stage pantallaJuego = new Stage();
                pantallaJuego.setScene(scene);
                pantallaJuego.show();
                Button b = (Button)event.getSource();
                Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
                s.close();
//                FXMLLoader fxml = App.loadFXML("VistaDomino");
//                Scene juego = new Scene(fxml.load());
//                Stage pantallajuego = new Stage();
//                pantallajuego.setScene(juego);
//                pantallajuego.show();
                
            }
            catch(IOException e){
                System.out.println("No se pudo abrir el canal"+e.getMessage());
                e.printStackTrace();
            }
            
        }
    }

}
