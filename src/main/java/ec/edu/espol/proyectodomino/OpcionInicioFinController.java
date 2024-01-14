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
    private Stage nuevaVentana;
    
    public static boolean posicionInicioFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            //Abrir selecNumController
            //ventanaSelecNum
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarNum.fxml"));
            // Crear la nueva ventana
            nuevaVentana = new Stage();
            // Configurar la escena y mostrar la nueva ventana
            Scene scene;

            scene = new Scene(loader.load(), 400, 400);
            nuevaVentana.setScene(scene);
        }catch(IOException e){
            System.out.println("No se pudo abrir el canal");
            e.printStackTrace();
        }
    }    
   
    
    @FXML
    public void inicioPress(ActionEvent event) {
        Stage stage = (Stage)this.botonInicio.getScene().getWindow();
        posicionInicioFin = true;
        nuevaVentana.showAndWait();
        stage.close();
        
    }

    @FXML
    public void finPress(ActionEvent event) {
        Stage stage = (Stage)this.botonFin.getScene().getWindow();
        posicionInicioFin = false;
        nuevaVentana.showAndWait();
        stage.close();
    }
    
}
