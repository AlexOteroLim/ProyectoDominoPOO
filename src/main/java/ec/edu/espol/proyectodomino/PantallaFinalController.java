/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class PantallaFinalController implements Initializable {

    @FXML
    private AnchorPane pantalla;
    @FXML
    private Text textFinal;
    
    private boolean ganador;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ganador = true; //Ingresar el resultado del juego
        if (ganador){
            textFinal.setText("GANASTE");
        }
        else{
            textFinal.setText("PERDISTE");
        }
    }    
    
}
