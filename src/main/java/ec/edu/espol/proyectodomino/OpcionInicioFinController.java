/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import JuegoDomino.FichaComodin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class OpcionInicioFinController implements Initializable {
    private FichaComodin fcselected;
    @FXML
    private Button botonInicio;
    @FXML
    private Button botonFin;
    
    public static boolean posicionInicioFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
   
    
    @FXML
    public void inicioPress(ActionEvent event) {
        posicionInicioFin = true;
        try{
            FXMLLoader fxml = App.loadFXML("SeleccionarNum");
            Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
            Stage st = new Stage();
            st.setScene(cs);
            st.show();
            Button b = (Button)event.getSource();
            Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
            s.close();
        }catch(IOException e){
            System.out.println("No se pudo abrir el canal");
            e.printStackTrace();
        }
        

          
    }

    @FXML
    public void finPress(ActionEvent event) {
        posicionInicioFin = false;
        try{
            FXMLLoader fxml = App.loadFXML("SeleccionarNum");
            Scene cs = new Scene(fxml.load(),600,600); //1. cargar el controller en una escena
            Stage st = new Stage();
            st.setScene(cs);
            st.show();
            Button b = (Button)event.getSource();
            Stage s = (Stage)b.getScene().getWindow(); //window no es un stage pero se puede setear en un (stage)
            s.close();
        }catch(IOException e){
            System.out.println("No se pudo abrir el canal"+e.getMessage());
        }
    }

    public boolean isPosicionInicioFin() {
        return posicionInicioFin;
    }
    
}
