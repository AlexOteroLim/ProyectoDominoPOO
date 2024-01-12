/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import JuegoDomino.FichaComodin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void setPelicula(FichaComodin fc){   //Importante para datos dinámicos entre cartelera y horario
        fcselected = fc;
               
    }
    
    @FXML
    private void inicioPress(ActionEvent event) {
    }

    @FXML
    private void finPress(ActionEvent event) {
    }
    
}
