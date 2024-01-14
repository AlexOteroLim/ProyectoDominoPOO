/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectodomino;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier Otero
 */
public class PantallaFinalController implements Initializable {

    @FXML
    private Text textFinal;
    
    private boolean ganador;
    @FXML
    private ImageView imgPFinal;
    @FXML
    private Button acepBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ganador = DominoController.jGanador; //Ingresar el resultado del juego
        if (ganador){
            imgPFinal.setImage(new Image(getClass().getResourceAsStream("/imagenesOtros/corona.png")));
            imgPFinal.setFitHeight(250);
            imgPFinal.setFitWidth(250);
            textFinal.setText("Ganaste");
        }
        else{
            imgPFinal.setImage(new Image(getClass().getResourceAsStream("/imagenesOtros/calavera.jpg")));
            imgPFinal.setFitHeight(250);
            imgPFinal.setFitWidth(250);
            textFinal.setText("Perdiste");
        }
    }    

    @FXML
    private void acepBtnAct(ActionEvent event) {
        Stage stage = (Stage)this.acepBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
    
}
